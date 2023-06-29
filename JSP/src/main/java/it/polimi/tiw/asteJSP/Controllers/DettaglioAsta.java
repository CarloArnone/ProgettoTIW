package it.polimi.tiw.asteJSP.Controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.polimi.tiw.asteJSP.Beans.ArticoloBean;
import it.polimi.tiw.asteJSP.Beans.OffertaBean;
import it.polimi.tiw.asteJSP.Beans.UserBean;
import it.polimi.tiw.asteJSP.DAO.ArticoloDao;
import it.polimi.tiw.asteJSP.DAO.AstaDao;
import it.polimi.tiw.asteJSP.DAO.OfferteDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "dettaglioasta", value = "/AuctionDetail")
public class DettaglioAsta extends HttpServlet {

    Connection connection;
    @Override
    public void init() throws ServletException {

        try {
            ServletContext context = getServletContext();
            String driver = context.getInitParameter("dbDriver");
            String url = context.getInitParameter("dbUrl");
            String user = context.getInitParameter("dbUser");
            String password = context.getInitParameter("dbPassword");
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException e) {
            throw new UnavailableException("Can't load database driver");
        } catch (SQLException e) {
            throw new UnavailableException("Couldn't get db connection");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{

            Gson writer = new Gson();
            JsonObject jsonObject = new JsonObject();
            AstaDao daoAsta = new AstaDao(connection);
            int auctionId = Integer.parseInt(req.getParameter("idAsta"));
            jsonObject.addProperty("auction", writer.toJson(daoAsta.getAstaById(auctionId)));
            UserBean userBean = (UserBean) req.getSession().getAttribute("user");

            OfferteDao daoOfferta = new OfferteDao(connection);
            List<OffertaBean> offerte = daoOfferta.getOffersOfAuction(auctionId);

            jsonObject.addProperty("offers", writer.toJson(offerte));

            ArticoloDao daoArticolo = new ArticoloDao(connection);
            List<ArticoloBean> articoli = daoArticolo.getArticoliDiAsta(auctionId);

            jsonObject.addProperty("articles", writer.toJson(articoli));
            jsonObject.addProperty("user", writer.toJson(userBean));

            resp.getWriter().write(writer.toJson(jsonObject));
        } catch (Exception e) {
            resp.getWriter().write("Error");
            return;
        }
    }


        @Override
    public void destroy() {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
}
