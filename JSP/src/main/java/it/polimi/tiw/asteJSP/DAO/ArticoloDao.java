package it.polimi.tiw.asteJSP.DAO;

import it.polimi.tiw.asteJSP.Beans.ArticoloBean;

import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public void aggiungiArticolo(int idCreatore, String articleName, int price, String description, Part image) throws SQLException, IOException {
        String query = "INSERT INTO articoli (idCreatore, nome, prezzo, descrizione, immagine) VALUES (?, ?, ?, ?, ?) ";
        Path imagePath = Paths.get(findCorrectPathFromResources("")).resolve(image.getSubmittedFileName());
        Files.copy(image.getInputStream(), imagePath,  StandardCopyOption.REPLACE_EXISTING);
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1, idCreatore);
        pst.setString(2, articleName);
        pst.setInt(3, price);
        pst.setString(4, description);
        pst.setString(5, imagePath.toString());

        pst.executeUpdate();
    }


    public static String findCorrectPathFromResources(String pathFromRes){
        return "C:\\Users\\carlo\\Desktop\\immagini\\";
    }

}
