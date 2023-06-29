package it.polimi.tiw.asteJSP.Controllers;

import it.polimi.tiw.asteJSP.Beans.AstaBean;
import it.polimi.tiw.asteJSP.Beans.UserBean;
import it.polimi.tiw.asteJSP.DAO.AstaDao;

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

@WebServlet(name = "closeAuction", value = "/CloseAuction")
public class CloseAuction extends HttpServlet {
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
            int userId = ((UserBean)req.getSession().getAttribute("user")).getId();
            AstaDao astaDao = new AstaDao(connection);
            AstaBean asta = astaDao.getAstaById(Integer.parseInt(req.getParameter("idAsta")));

            if(asta != null){
                if(userId == asta.getIdCreatore() && asta.getOreRimantenti() >= 0){
                    astaDao.closeAuction(asta.getId());
                }
                else{
                    resp.getWriter().write("Error");
                    return;
                }
            }
            else{
                resp.getWriter().write("Error");
                return;
            }

        } catch (SQLException e) {
            resp.getWriter().write("Error");
            return;
            //HANDLE NOT EXISTING AUCTION
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
