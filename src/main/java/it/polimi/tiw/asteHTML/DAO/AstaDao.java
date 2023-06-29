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
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1, auctionId);

        ResultSet r1 = pst.executeQuery();
        if(!r1.isBeforeFirst()){
            return null;
        }

        r1.next();
        if (r1.getBoolean("closed")) {
            String queryFinale = "SELECT * FROM astechiuse WHERE id = ?";
            PreparedStatement pst1 = connection.prepareStatement(queryFinale);
            pst1.setInt(1, auctionId);
            ResultSet res = pst1.executeQuery();
            if(!res.isBeforeFirst()){
                return null;
            }

            res.next();
            UtenteDao utenteDao = new UtenteDao(connection);
            return new AstaBean( res.getInt("id"),
                    res.getInt("idCreatore"),
                    utenteDao.getUserNameById(res.getInt("idCreatore")),
                    utenteDao.getUserNameById(res.getInt("idVincitore")),
                    res.getInt("idVincitore"),
                    res.getInt("prezzoFinale"),
                    res.getDate("dataTermine"),
                    res.getString("indirizzoSpedizione"));
        } else {
            String queryFinale = "SELECT * FROM asteaperte WHERE id = ?";
            PreparedStatement pst1 = connection.prepareStatement(queryFinale);
            pst1.setInt(1, auctionId);

            ResultSet res = pst1.executeQuery();
            if(!res.isBeforeFirst()){
                return null;
            }

            res.next();
            UtenteDao utenteDao = new UtenteDao(connection);
            AstaBean asta =  new AstaBean( res.getInt("id"),
                    res.getInt("idCreatore"),
                    utenteDao.getUserNameById(res.getInt("idCreatore")),
                    res.getInt("prezzoMassimoRaggiunto"),
                    res.getInt("rialzoMinimo"),
                    res.getDate("dataTermine"),
                    res.getInt("oreRimanenti"));

            pst.close();
            pst1.close();

            return asta;

        }

    }
    private List<AstaBean> getAsteAperteBeans(PreparedStatement pst) throws SQLException {
        ResultSet res = pst.executeQuery();

        List<AstaBean> toReturn = new ArrayList<>();
        UtenteDao utenteDao = new UtenteDao(connection);
        while(res.next()){
            toReturn.add(new AstaBean( res.getInt("id"),
                    res.getInt("idCreatore"),
                    utenteDao.getUserNameById(res.getInt("idCreatore")),
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
        UtenteDao utenteDao = new UtenteDao(connection);
        while(res.next()){
            toReturn.add(new AstaBean( res.getInt("id"),
                                        res.getInt("idCreatore"),
                                        utenteDao.getUserNameById(res.getInt("idCreatore")),
                                        utenteDao.getUserNameById(res.getInt("idCreatore")),
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
    public List<AstaBean> getAsteAperteContententi(String query) throws SQLException {
        String queryFind = "SELECT id FROM asteaperte WHERE id IN ( SELECT idAsta FROM articoli WHERE descrizione LIKE '%" + query + "%' OR nome LIKE '%" + query + "%')";
        PreparedStatement pst = connection.prepareStatement(queryFind);

        ResultSet resultSet = pst.executeQuery();
        if(!resultSet.isBeforeFirst()){
            return null;
        }

        List<AstaBean> toReturn = new ArrayList<>();

        while (resultSet.next()){
            toReturn.add(getAstaById(resultSet.getInt("id")));
        }

        return toReturn;
    }

    public List<AstaBean> getAsteVinteDa(int userId) throws SQLException {
        String query = "SELECT * FROM astechiuse WHERE idVincitore = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1, userId);

        ResultSet resultSet = pst.executeQuery();

        if(!resultSet.isBeforeFirst()){
            return null;
        }

        List<AstaBean> toReturn = new ArrayList<>();

        while(resultSet.next()){
            toReturn.add(getAstaById(resultSet.getInt("id")));
        }
        return toReturn;
    }


    public void closeAuction(int id) {
        String query = "UPDATE aste SET closed = true WHERE id = ?";
        try(PreparedStatement pst = connection.prepareStatement(query)){
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
