package it.polimi.tiw.asteHTML.Controllers;

import it.polimi.tiw.asteHTML.Beans.UserBean;
import it.polimi.tiw.asteHTML.DAO.ArticoloDao;
import it.polimi.tiw.asteHTML.Exception.NotImageExeption;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "createarticle", value = "/CreateArticle")
@MultipartConfig
public class CreateArticle extends HttpServlet {
    Connection connection;
    private final List<String> validFormats = List.of(new String[]{"image/jpeg", "image/jpg"});
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
            String articleName = req.getParameter("nomearticolo");
            int prezzo = Integer.parseInt(req.getParameter("prezzo"));
            String descrizione = req.getParameter("descrizione");
            Part immagine = req.getPart("immagine");

            //if(! validFormats.contains(immagine.getContentType())){
            //    throw new NotImageExeption();
            //}

            int userId = ((UserBean)req.getSession().getAttribute("user")).getId();


            if(articleName.equals("") || descrizione.equals("")){
                req.setAttribute("error", "There is an error in the inputs");
                String context = getServletContext().getContextPath();
                resp.sendRedirect(context + "/Vendo");
            }

            ArticoloDao dao = new ArticoloDao(connection);
            try {
                dao.aggiungiArticolo(userId, articleName, prezzo, descrizione, immagine);
            } catch (SQLException e) {
                req.setAttribute("error", "There is some error in the inputs");
            }
        } catch (ServletException ignored) {
        } finally {
            String context = getServletContext().getContextPath();
            resp.sendRedirect(context + "/Vendo");
        }
    }
}
