package ru.nikita.configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/user/*","/admin/*"})
public class AuthenticationFilter implements javax.servlet.Filter {
    private ServletContext context;
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;



        HttpSession session = req.getSession(false);
        String role = (String) session.getAttribute("userRole");

        String loginURI = req.getContextPath() + "/";

        boolean loggedIn = session != null && role != null;
        boolean loginRequest = req.getRequestURI().equals(loginURI);
        System.out.println(req.getRequestURI());



        if (loggedIn || loginRequest) {
            switch (role){
                case "user" :
                    if (req.getRequestURI().equals("/user/")){
                        chain.doFilter(req, res);
                    } else {
                        //chain.doFilter(req, res);
                        res.sendRedirect("/user/");
                    }
                break;
                case "admin" : chain.doFilter(req, res);
                break;
            }
        } else {
            res.sendRedirect(loginURI);
        }
    }

    public void init(FilterConfig config) throws ServletException {
//        this.context = config.getServletContext();
//        this.context.log("AuthenticationFilter initialized");

    }

}
