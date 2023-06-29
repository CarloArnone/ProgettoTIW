package it.polimi.tiw.asteJSP.Controllers;


import javax.servlet.ServletContext;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import it.polimi.tiw.asteJSP.DAO.*;
import it.polimi.tiw.asteJSP.Beans.*;

@WebServlet(name = "PaginaVendo", value = "/Vendo")
public class Vendo extends HttpServlet {
    private Connection connection;

    public void init() throws UnavailableException {
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

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        AstaDao dao = new AstaDao(connection);
        int userId = ((UserBean)request.getSession().getAttribute("user")).getId();
        String jsonString = "";
        //VERIFY THAT THE USER SHOULD BE THE CORRECT ONE
        try{
            List<AstaBean> asteAperte = dao.getAsteAperteDi(userId);
            List<AstaBean> asteChiuse = dao.getAsteChiuseDi(userId);

            ArticoloDao articoloDao = new ArticoloDao(connection);
            List<ArticoloBean> sellableArticles = articoloDao.getArticoliVendibiliDi(userId);

            Gson writer = new Gson();
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("asteAperte", writer.toJson(asteAperte));
            jsonObject.addProperty("asteChiuse", writer.toJson(asteChiuse));
            jsonObject.addProperty("sellableArticles", writer.toJson(sellableArticles));
            jsonString += writer.toJson(jsonObject);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.getWriter().write(jsonString);

    }

    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
