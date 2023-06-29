package it.polimi.tiw.asteJSP.Controllers;

import it.polimi.tiw.asteJSP.Beans.UserBean;
import it.polimi.tiw.asteJSP.DAO.UtenteDao;
import org.apache.commons.lang3.StringEscapeUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@WebServlet("/LoginPage")
@MultipartConfig
public class LoginPage extends HttpServlet {

    Connection connection;

    public LoginPage(){
        super();
    }

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = null;
        String pwd = null;
        username = StringEscapeUtils.escapeJava(req.getParameter("username"));
        pwd = StringEscapeUtils.escapeJava(req.getParameter("pwd"));


        UtenteDao userDao = new UtenteDao(connection);
        try {
            UserBean user =  userDao.checkCredentials(username, pwd);
            if(user == null){
                resp.getWriter().write("Error");
                return;
            }
            req.getSession().setAttribute("user", user);
            resp.getWriter().write("Success," + user.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroy() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException sqle) {
        }
    }
}
