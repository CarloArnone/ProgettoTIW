package it.polimi.tiw.asteHTML.Controllers;

import it.polimi.tiw.asteHTML.Beans.ArticoloBean;
import it.polimi.tiw.asteHTML.Beans.AstaBean;
import it.polimi.tiw.asteHTML.Beans.OffertaBean;
import it.polimi.tiw.asteHTML.Beans.UserBean;
import it.polimi.tiw.asteHTML.DAO.ArticoloDao;
import it.polimi.tiw.asteHTML.DAO.AstaDao;
import it.polimi.tiw.asteHTML.DAO.OfferteDao;
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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "dettaglioasta", value = "/DettaglioAsta")
public class DettaglioAsta extends HttpServlet {

    Connection connection;
    TemplateEngine templateEngine;
    @Override
    public void init() throws ServletException {
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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
        String path = "/auctiondetail.html";



        try{
            AstaDao daoAsta = new AstaDao(connection);
            int auctionId = Integer.parseInt(req.getParameter("idAsta"));
            ctx.setVariable("auction", daoAsta.getAstaById(auctionId));

            OfferteDao daoOfferta = new OfferteDao(connection);
            List<OffertaBean> offerte = daoOfferta.getOffersOfAuction(auctionId);

            ctx.setVariable("offers", offerte);

            ArticoloDao daoArticolo = new ArticoloDao(connection);
            List<ArticoloBean> articoli = daoArticolo.getArticoliDiAsta(auctionId);

            ctx.setVariable("articles", articoli);
        } catch (SQLException e) {
            path = "/home.html";
        }
        try{
            String error = req.getParameter("error");
            ctx.setVariable("error", error);
        }
        catch (Exception e){

        }
        templateEngine.process(path, ctx, resp.getWriter());
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
