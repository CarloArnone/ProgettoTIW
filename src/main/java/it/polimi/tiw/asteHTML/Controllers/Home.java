package it.polimi.tiw.asteHTML.Controllers;

import it.polimi.tiw.asteHTML.Beans.UserBean;
import it.polimi.tiw.asteHTML.DAO.UtenteDao;
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

@WebServlet(name = "loginPage", value = "/LoginPage", loadOnStartup = 1)
public class Home extends HttpServlet {
    private TemplateEngine templateEngine;
    private Connection connection;

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
        String path = "/home.html";
        templateEngine.process(path, ctx, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UtenteDao userDao = new UtenteDao(connection);

        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());

        UserBean user = null;
        String path = "";


        try{
            boolean isSignUp = req.getParameter("isSignUp").equals("on");
            String confpwd = "";
            String indSped = "";
            String usernameSU = "";
            String pwdSU = "";
            ctx.setVariable("signUpView", "checked");
            try{
                usernameSU = req.getParameter("usernamesu");
                pwdSU = req.getParameter("pwdsu");
                confpwd = req.getParameter("confpwd");
                indSped = req.getParameter("indsped");

                ctx.setVariable("oldUserNameSU", usernameSU);
                ctx.setVariable("oldIndSped", indSped);
            }
            catch (Exception e){
                path += "/login.html";
                ctx.setVariable("error", "Stop tweaking the application");
                templateEngine.process(path, ctx, resp.getWriter());
                return;
            }
            try{
                if(!usernameSU.equals("")){
                    if(!pwdSU.equals("")){
                        if(!confpwd.equals("")){
                            if(! indSped.equals("")){
                                if(pwdSU.equals(confpwd)){
                                    if(! userDao.isUserPresent(usernameSU)){
                                        user = userDao.insertUser(usernameSU, pwdSU, indSped);
                                        req.getSession().setAttribute("user", user);
                                        path += "/home.html";
                                    }
                                    else {
                                        path += "/login.html";
                                        ctx.setVariable("error", "Username already in use");
                                    }
                                }
                                else {
                                    path += "/login.html";
                                    ctx.setVariable("error", "Passwords do not match");
                                }

                            }
                            else {
                                path += "/login.html";
                                ctx.setVariable("error", "Specify a shipping address");
                            }
                        }
                        else {
                            path += "/login.html";
                            ctx.setVariable("error", "Confirm password is required");
                        }
                    }
                    else {
                        path += "/login.html";
                        ctx.setVariable("error", "Password is required");
                    }
                }
                else {
                    path += "/login.html";
                    ctx.setVariable("error", "Username is required");
                }

            } catch (SQLException e) {
                resp.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Exception when checking credentials");
            }
        }
        catch (Exception e){
            String username = "";
            String pwd = "";
            try{
                username = req.getParameter("username");
                pwd = req.getParameter("pwd");

                ctx.setVariable("oldUserName", username);
                if(req.getAttribute("errorNotLoggedIn") != null){
                    ctx.setVariable("errorLogin", req.getAttribute("errorNotLoggedIn"));
                }
            }
            catch (Exception ex){
                path += "/login.html";
                ctx.setVariable("errorLogin", "Stop tweaking the application");
                templateEngine.process(path, ctx, resp.getWriter());
                return;
            }

            try {
                user = userDao.checkCredentials(username, pwd);
            } catch (SQLException es) {
                resp.sendError(HttpServletResponse.SC_BAD_GATEWAY, "Exception when checking credentials");
            }
            if(user == null){
                path += "/login.html";
                ctx.setVariable("errorLogin", "username o password errati");
            }
            else{
                req.getSession().setAttribute("user", user);
                path += "/home.html";
            }
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
