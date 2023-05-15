package it.polimi.tiw.asteHTML.Controllers;

import it.polimi.tiw.asteHTML.Beans.AstaBean;
import it.polimi.tiw.asteHTML.Beans.UserBean;
import it.polimi.tiw.asteHTML.DAO.AstaDao;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.servlet.ServletContext;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


@WebServlet(name = "PaginaAcquisto", value = "/Acquisto")
public class Acquisto extends HttpServlet {
    TemplateEngine templateEngine;
    Connection connection;

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
        String path = "/acquistopage.html";

        AstaDao astaDao = new AstaDao(connection);
        int userId = ((UserBean)request.getSession().getAttribute("user")).getId();

        try {
            String qr;

            qr = request.getParameter("query");

            if(qr == null){
                qr = "";
            }

            List<AstaBean> asteAperte = astaDao.getAsteAperteContententi(qr);
            List<AstaBean> asteChiuse = astaDao.getAsteVinteDa(userId);

            ctx.setVariable("asteaperte", asteAperte);
            ctx.setVariable("astechiuse", asteChiuse);
            ctx.setVariable("query", request.getAttribute("query"));

        } catch (SQLException e) {
            ctx.setVariable("errorLoading", "there was an error loading the page");
        }
        finally {
            templateEngine.process(path, ctx, response.getWriter());
        }
    }

    public void destroy() {
    }
}
