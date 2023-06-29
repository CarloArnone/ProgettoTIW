package it.polimi.tiw.asteJSP.Controllers;

import it.polimi.tiw.asteJSP.Beans.UserBean;
import it.polimi.tiw.asteJSP.DAO.ArticoloDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
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


            if( articleName == null || descrizione == null || articleName.equals("") || descrizione.equals("") || prezzo < 0){
                resp.getWriter().write("Error");
                return;
            }

            ArticoloDao dao = new ArticoloDao(connection);
            dao.aggiungiArticolo(userId, articleName, prezzo, descrizione, immagine);
            resp.getWriter().write("Success");

        } catch (ServletException | SQLException eee) {
            resp.getWriter().write("Error");
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
