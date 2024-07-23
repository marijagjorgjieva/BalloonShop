package mk.finki.ukim.mk.lab3.web.filter;

import mk.finki.ukim.mk.lab3.model.User;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
@Profile("servlet")
public class Filter implements javax.servlet.Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user = (User)request.getSession().getAttribute("user");

        String path = request.getServletPath();

        if (!"/login".equals(path) &&
                !"/h2".equals(path) &&
                !"/register".equals(path) &&
                !"/css/styles.css".equals(path) && user==null) {
            response.sendRedirect("/login");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

}
