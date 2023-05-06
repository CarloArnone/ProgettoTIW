package it.polimi.tiw.asteHTML.DAO;

import java.sql.Connection;

public class OfferteDao {
    private Connection connection;

    public OfferteDao(Connection connection) {
        this.connection = connection;
    }
}
