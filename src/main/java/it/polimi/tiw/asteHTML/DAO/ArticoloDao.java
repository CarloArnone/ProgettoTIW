package it.polimi.tiw.asteHTML.DAO;

import it.polimi.tiw.asteHTML.Beans.ArticoloBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ArticoloDao {
    private Connection connection;

    public ArticoloDao(Connection connection) {
        this.connection = connection;
    }

    public boolean inserisciArticoloInAsta(int idAsta, int idArticolo) {
        String query = "UPDATE articoli SET idAsta = ? WHERE id = ?";
        try{
            PreparedStatement pst = connection.prepareStatement(query);
            pst.setInt(1, idAsta);
            pst.setInt(2, idArticolo);
            pst.executeUpdate();

            String queryPrezzo = "SELECT prezzo FROM articoli WHERE id = ?";
            PreparedStatement p1 = connection.prepareStatement(queryPrezzo);
            p1.setInt(1, idArticolo);
            ResultSet res = p1.executeQuery();
            res.next();
            int prezzoArticolo = res.getInt("prezzo");


            String updatePrezzoIniziale = "UPDATE aste SET prezzoIniziale = prezzoIniziale + ? WHERE id = ? ";
            PreparedStatement p = connection.prepareStatement(updatePrezzoIniziale);
            p.setInt(1, prezzoArticolo);
            p.setInt(2, idAsta);
            p.executeUpdate();

            return true;
        }
        catch(SQLException e){
            return false;
        }

    }

    public List<ArticoloBean> getArticoliDiAsta(int idAsta) throws SQLException {
        String query = "SELECT * FROM articoli WHERE idAsta = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1, idAsta);
        ResultSet res = pst.executeQuery();
        if(!res.isBeforeFirst()){
            return null;
        }
        List<ArticoloBean> toReturn = new ArrayList<>();
        while (res.next()){
            toReturn.add(new ArticoloBean(  res.getInt("id"),
                                            res.getInt("idCreatore"),
                                            idAsta,
                                            res.getInt("prezzo"),
                                            res.getString("nome"),
                                            res.getString("immagine"),
                                            res.getString("descrizione")));
        }

        return toReturn;
    }

    public List<ArticoloBean> getArticoliVendibiliDi(int userId) throws SQLException {
        String query = "SELECT * FROM articoli WHERE idAsta IS NULL AND idCreatore = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1, userId);

        ResultSet res = pst.executeQuery();
        if(!res.isBeforeFirst()){
            return null;
        }
        List<ArticoloBean> toReturn = new ArrayList<>();
        while(res.next()){
            toReturn.add(new ArticoloBean(  res.getInt("id"),
                                            res.getInt("idCreatore"),
                                            res.getInt("prezzo"),
                                            res.getString("nome"),
                                            res.getString("immagine"),
                                            res.getString("descrizione")));
        }

        return toReturn;
    }

    public void aggiungiArticolo(int idCreatore, String articleName, int price, String description, String image) throws SQLException {
        String query = "INSERT INTO articoli (idCreatore, nome, prezzo, descrizione, immagine) VALUES (?, ?, ?, ?, ?) ";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1, idCreatore);
        pst.setString(2, articleName);
        pst.setInt(3, price);
        pst.setString(4, description);
        pst.setString(5, image);

        pst.executeUpdate();
    }

}
