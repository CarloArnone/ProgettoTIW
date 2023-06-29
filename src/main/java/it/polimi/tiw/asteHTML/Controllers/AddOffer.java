package it.polimi.tiw.asteHTML.Controllers;

import it.polimi.tiw.asteHTML.Beans.AstaBean;
import it.polimi.tiw.asteHTML.Beans.UserBean;
import it.polimi.tiw.asteHTML.DAO.AstaDao;
import it.polimi.tiw.asteHTML.DAO.OfferteDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet(name = "addOffer", value = "/AddOffer")
public class AddOffer  extends HttpServlet {
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

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String request = getServletContext().getContextPath() + "/DettaglioAsta?";
        try{
            int bid = Integer.parseInt(req.getParameter("bid"));
            int userId = ((UserBean)req.getSession().getAttribute("user")).getId();
            AstaBean asta = new AstaDao(connection).getAstaById(Integer.parseInt(req.getParameter("idAsta")));

            request += "idAsta=" + asta.getId();
            if( bid >= asta.getPrezzoRaggiunto() + asta.getRialzoMinimo()
                    && asta.getIdCreatore() != userId
                    && asta.getOreRimantenti() <= 0){
                new OfferteDao(connection).insertOffer(userId, asta.getId(), bid);
            }
            else{
                request += "&error=BitNotValid";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            resp.sendRedirect(request);
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
