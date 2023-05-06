package it.polimi.tiw.asteHTML.DAO;

import it.polimi.tiw.asteHTML.Beans.AstaBean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AstaDao {
    private Connection connection;

    public AstaDao(Connection connection) {
        this.connection = connection;
    }

    public List<AstaBean> getAsteAperteDi(int userID) throws SQLException {
        String query = "SELECT * FROM asteAperte WHERE idCreatore = ?";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1, userID);
        ResultSet res = pst.executeQuery();

        return null;
    }
}
