package it.polimi.tiw.asteJSP.DAO;

import it.polimi.tiw.asteJSP.Beans.UserBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UtenteDao {

    private Connection connection;

    public UtenteDao(Connection connection) {
        this.connection = connection;
    }

    public UserBean checkCredentials(String username, String password) throws SQLException {
        String query = "SELECT * FROM utenti WHERE userName = ? AND passWord = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);

        ResultSet res = pst.executeQuery();

        if(! res.isBeforeFirst()){
            return null;
        }
        else{
            res.next();
            String queryUpdateLastLogin = "UPDATE utenti SET lastLogin = CURRENT_TIMESTAMP WHERE userName = ?";
            PreparedStatement pst1 = connection.prepareStatement(queryUpdateLastLogin);
            pst1.setString(1, username);
            pst1.executeUpdate();
            return new UserBean(res.getInt("id"),
                                username,
                                res.getString("indirizzoSpedizione"),
                                res.getString("lingua"));
            }
    }


    public boolean isUserPresent(String username) throws SQLException {
        String query = "SELECT userName FROM utenti WHERE userName = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, username);
        ResultSet res = pst.executeQuery();
        return res.isBeforeFirst();
    }

    public UserBean insertUser(String username, String password, String indSped) throws SQLException {
        String query = "INSERT INTO utenti (userName, passWord, indirizzoSpedizione) VALUES (?, ?, ?)";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setString(1, username);
        pst.setString(2, password);
        pst.setString(3, indSped);

        pst.executeUpdate();
        return checkCredentials(username, password);
    }

    public String getUserNameById(int id) throws SQLException {
        String query = "SELECT userName FROM utenti WHERE id = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet res = pst.executeQuery();
        if(!res.isBeforeFirst()){
            return null;
        }
        res.next();

        return res.getString("userName");
    }

}
