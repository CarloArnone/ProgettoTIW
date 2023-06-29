package it.polimi.tiw.asteJSP.Controllers;

import it.polimi.tiw.asteJSP.Beans.AstaBean;
import it.polimi.tiw.asteJSP.Beans.UserBean;
import it.polimi.tiw.asteJSP.DAO.AstaDao;
import it.polimi.tiw.asteJSP.DAO.OfferteDao;

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

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            int bid = Integer.parseInt(req.getParameter("bid"));
            int userId = ((UserBean)req.getSession().getAttribute("user")).getId();
            AstaBean asta = new AstaDao(connection).getAstaById(Integer.parseInt(req.getParameter("idAsta")));

            if( bid >= asta.getPrezzoRaggiunto() + asta.getRialzoMinimo()
                    && asta.getIdCreatore() != userId
                    && asta.getOreRimantenti() <= 0){
                new OfferteDao(connection).insertOffer(userId, asta.getId(), bid);
            }
            else{
                resp.getWriter().write("Error");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        resp.getWriter().write(req.getParameter("idAsta"));
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
