package it.polimi.tiw.asteHTML.Controllers;

import it.polimi.tiw.asteHTML.Beans.ArticoloBean;
import it.polimi.tiw.asteHTML.Beans.AstaBean;
import it.polimi.tiw.asteHTML.Beans.UserBean;
import it.polimi.tiw.asteHTML.DAO.ArticoloDao;
import it.polimi.tiw.asteHTML.DAO.AstaDao;
import it.polimi.tiw.asteHTML.DAO.UtenteDao;
import it.polimi.tiw.asteHTML.Exception.NotAuctionInsert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(name = "PaginaVendo", value = "/Vendo")
public class Vendo extends HttpServlet {
    private TemplateEngine templateEngine;
    private Connection connection;

    public void init() throws UnavailableException {
        ServletContext servletContext = getServletContext();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setPrefix("WEB-INF");
        templateResolver.setSuffix(".html");

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

        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        String path = "/vendopage.html";

        AstaDao dao = new AstaDao(connection);
        int userId = ((UserBean)request.getSession().getAttribute("user")).getId();

        try{
            List<AstaBean> asteAperte = dao.getAsteAperteDi(userId);
            List<AstaBean> asteChiuse = dao.getAsteChiuseDi(userId);
            ctx.setVariable("asteaperte", asteAperte);
            ctx.setVariable("astechiuse", asteChiuse);

            Map<Integer, List<ArticoloBean>> listaArticoli = new HashMap<>();
            ArticoloDao articoloDao = new ArticoloDao(connection);
            for (AstaBean asta : asteAperte){
                listaArticoli.put(asta.getId(), articoloDao.getArticoliDiAsta(asta.getId()));
            }
            for (AstaBean asta : asteChiuse){
                listaArticoli.put(asta.getId(), articoloDao.getArticoliDiAsta(asta.getId()));
            }

            ctx.setVariable("articles", listaArticoli);
            ctx.setVariable("sellablearticles", articoloDao.getArticoliVendibiliDi(userId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        templateEngine.process(path, ctx, response.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            if(Boolean.parseBoolean(req.getParameter("createauction"))){
                int rialzoMin = Integer.parseInt(req.getParameter("rialzominimo"));
                String[] date = req.getParameter("datatermine").split("-");
                Date dataTermine = new Date(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));

                List<Integer> articlesId = new ArrayList<>();
                int userId = ((UserBean)req.getSession().getAttribute("user")).getId();
                List<ArticoloBean> articles = new ArticoloDao(connection).getArticoliVendibiliDi(userId);
                for(ArticoloBean article : articles){
                    try{
                        if(req.getParameter(String.valueOf(article.getId())).equals("on")){
                            articlesId.add(article.getId());
                        }
                    }
                    catch(Exception e){
                        continue;
                    }
                }

                AstaDao dao = new AstaDao(connection);
                try{
                    dao.inserisciAsta(articlesId, dataTermine, userId, rialzoMin);
                }
                catch (SQLException sqle){
                    System.out.println("Something wrong");
                }
            }
            else{
                throw new NotAuctionInsert();
            }
        }
        catch (Exception e){
            doGet(req, resp);
            return;
        } catch (NotAuctionInsert e) {
            try{
                String articleName = req.getParameter("nomearticolo");
                int prezzo = Integer.parseInt(req.getParameter("prezzo"));
                String descrizione = req.getParameter("descrizione");
                String immagine = req.getParameter("immagine");
                int userId = ((UserBean)req.getSession().getAttribute("user")).getId();

                if(articleName.equals("") || descrizione.equals("") || immagine.equals("")){
                    throw new SQLException();
                }




                ArticoloDao dao = new ArticoloDao(connection);
                dao.aggiungiArticolo(userId, articleName, prezzo, descrizione, immagine);
            }
            catch (Exception ex){
                doGet(req, resp);
                return;
            }
        }

        doGet(req, resp);
    }

    public void destroy() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
