package it.polimi.tiw.asteHTML.DAO;

import it.polimi.tiw.asteHTML.Beans.AstaBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AstaDao {
    private Connection connection;

    public AstaDao(Connection connection) {
        this.connection = connection;
    }

    public List<AstaBean> getAsteAperteDi(int userID) throws SQLException {
        String query = "SELECT * FROM asteaperte WHERE idCreatore = ? ORDER BY dataTermine";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1, userID);
        return getAsteAperteBeans(pst);
    }
    public List<AstaBean> getAsteAperte() throws SQLException {
        String query = "SELECT * FROM asteaperte ORDER BY dataTermine";
        PreparedStatement pst = connection.prepareStatement(query);
        return getAsteAperteBeans(pst);
    }
    public List<AstaBean> getAsteChiuseDi(int userID) throws SQLException {
        String query = "SELECT * FROM astechiuse WHERE idCreatore = ? ORDER BY dataTermine";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1, userID);
        return getAsteChiuseBeans(pst);
    }
    public List<AstaBean> getAsteChiuse() throws SQLException {
        String query = "SELECT * FROM astechiuse ORDER BY dataTermine";
        PreparedStatement pst = connection.prepareStatement(query);
        return getAsteChiuseBeans(pst);
    }
    public AstaBean getAstaById(int auctionId) throws SQLException {
        String query = "SELECT closed FROM aste WHERE id = ?";
        String queryFinale = "SELECT * FROM ? WHERE id = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        PreparedStatement pst1 = connection.prepareStatement(queryFinale);
        pst.setInt(1, auctionId);
        pst1.setInt(2, auctionId);

        ResultSet r1 = pst.executeQuery();
        if(!r1.isBeforeFirst()){
            return null;
        }

        r1.next();
        if (r1.getBoolean("closed")) {
            pst1.setString(1, "astechiuse");
            ResultSet res = pst1.executeQuery();
            return new AstaBean( res.getInt("id"),
                    res.getInt("idVincitore"),
                    res.getInt("prezzoFinale"),
                    res.getDate("dataTermine"),
                    res.getString("indirizzoSpedizione"));
        } else {
            pst1.setString(1, "asteaperte");
            ResultSet res = pst1.executeQuery();
            return new AstaBean( res.getInt("id"),
                    res.getInt("prezzoMassimoRaggiunto"),
                    res.getInt("rialzoMinimo"),
                    res.getDate("dataTermine"),
                    res.getInt("oreRimanenti"));

        }

    }
    private List<AstaBean> getAsteAperteBeans(PreparedStatement pst) throws SQLException {
        ResultSet res = pst.executeQuery();

        List<AstaBean> toReturn = new ArrayList<>();

        while(res.next()){
            toReturn.add(new AstaBean( res.getInt("id"),
                    res.getInt("prezzoMassimoRaggiunto"),
                    res.getInt("rialzoMinimo"),
                    res.getDate("dataTermine"),
                    res.getInt("oreRimanenti")));
        }

        return toReturn;
    }
    private List<AstaBean> getAsteChiuseBeans(PreparedStatement pst) throws SQLException {
        ResultSet res = pst.executeQuery();

        List<AstaBean> toReturn = new ArrayList<>();

        while(res.next()){
            toReturn.add(new AstaBean( res.getInt("id"),
                                        res.getInt("idVincitore"),
                                        res.getInt("prezzoFinale"),
                                        res.getDate("dataTermine"),
                                        res.getString("indirizzoSpedizione")));
        }

        return toReturn;
    }
    public void inserisciAsta(List<Integer> idArticoli, Date dataTermine, int userId, int rialzoMinimo) throws SQLException {

        connection.setAutoCommit(false);

        String queryInsertAsta = "INSERT INTO aste (idCreatore, rialzoMinimo, dataTermine, closed, prezzoIniziale) VALUES (?, ?, ?, false, 0)";
        PreparedStatement pst1 = connection.prepareStatement(queryInsertAsta, Statement.RETURN_GENERATED_KEYS);
        pst1.setInt(1, userId);
        pst1.setInt(2, rialzoMinimo);
        pst1.setDate(3, dataTermine);

        pst1.executeUpdate();
        ResultSet res = pst1.getGeneratedKeys();
        if(!res.isBeforeFirst()){
            throw new SQLException();
        }
        res.next();
        int idAsta = res.getInt(1);

        ArticoloDao articleDao = new ArticoloDao(connection);
        for(Integer articleId : idArticoli){
            articleDao.inserisciArticoloInAsta(idAsta, articleId);
        }

        connection.commit();
        connection.setAutoCommit(true);
    }
}
