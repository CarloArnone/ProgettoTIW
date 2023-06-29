package it.polimi.tiw.asteJSP.Controllers;

import it.polimi.tiw.asteJSP.Beans.ArticoloBean;
import it.polimi.tiw.asteJSP.Beans.UserBean;
import it.polimi.tiw.asteJSP.DAO.ArticoloDao;
import it.polimi.tiw.asteJSP.DAO.AstaDao;
import it.polimi.tiw.asteJSP.Exception.WrongDateExeption;

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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@WebServlet(name = "createauction", value = "/CreateAuction")
@MultipartConfig
public class CreateAuction extends HttpServlet {
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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try{
            String rialzoMinString = req.getParameter("rialzominimo");
            int rialzoMin = Integer.parseInt(rialzoMinString);
            java.util.Date dataTermine = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm").parse(req.getParameter("datatermine"));

            if(dataTermine.before(Calendar.getInstance().getTime())){ //TODO DEBUG
                resp.getWriter().write("Error");
                return;
            }


            List<Integer> articlesId = new ArrayList<>();
            int userId = ((UserBean)req.getSession().getAttribute("user")).getId();
            List<ArticoloBean> articles = null;
            try {
                articles = new ArticoloDao(connection).getArticoliVendibiliDi(userId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            for(ArticoloBean article : articles){
                try{
                    if(req.getParameter(String.valueOf(article.getId())).equals("on")){ // Find the articles in the req and add them to the list
                        articlesId.add(article.getId());
                    }
                }
                catch(Exception e){
                    continue;
                }
            }

            AstaDao dao = new AstaDao(connection);
            try{
                dao.inserisciAsta(articlesId, new Date(dataTermine.getTime()), userId, rialzoMin);
            }
            catch (SQLException sqle){
                try {
                    connection.rollback();
                    connection.setAutoCommit(true);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (ParseException e) {
            resp.getWriter().write("Error");
            return;
        }

        resp.getWriter().write("Success");

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
