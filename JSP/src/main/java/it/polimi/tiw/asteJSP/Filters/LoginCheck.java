package it.polimi.tiw.asteJSP.Filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCheck implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String loginpath = req.getServletContext().getContextPath() + "/index.html"; //TODO SOLVE PROBLEM WITH INDEX/LOGIN

        HttpSession s = req.getSession();
        if (s.isNew() || s.getAttribute("user") == null) {
            req.setAttribute("errorNotLoggedIn", "You Have To Be Logged In");
            res.sendRedirect(loginpath);
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}

