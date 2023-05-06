package it.polimi.tiw.asteHTML.DAO;

import java.sql.Connection;

public class ArticoloDao {
    private Connection connection;

    public ArticoloDao(Connection connection) {
        this.connection = connection;
    }
}
