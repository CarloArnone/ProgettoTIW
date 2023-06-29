package it.polimi.tiw.asteJSP.DAO;

import it.polimi.tiw.asteJSP.Beans.OffertaBean;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OfferteDao {
    private Connection connection;

    public OfferteDao(Connection connection) {
        this.connection = connection;
    }

    public List<OffertaBean> getOffersOfAuction(int auctionId) throws SQLException {
        String query = "SELECT * FROM offerte WHERE idAsta= ? ORDER BY dataOfferta DESC";

        try(PreparedStatement pst = connection.prepareStatement(query)){
            pst.setInt(1, auctionId);
            ResultSet res = pst.executeQuery();
            if(!res.isBeforeFirst()){
                return null;
            }
            List<OffertaBean> toReturn = new ArrayList<>();
            while(res.next()){
                toReturn.add(new OffertaBean(
                        res.getInt("idCreatore"),
                        res.getInt("idAsta"),
                        res.getInt("prezzoOfferto"),
                        res.getDate("dataOfferta")
                ));
            }
            return toReturn;
        }
    }

    public void insertOffer(int idCreatore, int idAsta, int prezzoOfferto) throws SQLException {
        String query = "INSERT INTO offerte VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
        PreparedStatement pst = connection.prepareStatement(query);
        pst.setInt(1, idCreatore);
        pst.setInt(2, idAsta);
        pst.setInt(3, prezzoOfferto);

        pst.executeUpdate();
    }

}
