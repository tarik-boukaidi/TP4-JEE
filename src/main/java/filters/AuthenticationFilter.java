package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);
        String requestURI = httpRequest.getRequestURI();

        if (isPublicResource(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        if (session == null || session.getAttribute("userId") == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
            return;
        }

        if (isAdminOperation(requestURI)) {
            String role = (String) session.getAttribute("role");
            if (!"ADMIN".equals(role)) {
                httpRequest.setAttribute("error", "Accès refusé. Seuls les administrateurs peuvent effectuer cette action.");
                httpRequest.getRequestDispatcher("/WEB-INF/views/listProduits.jsp").forward(request, response);
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private boolean isPublicResource(String requestURI) {
        return requestURI.endsWith("/index.jsp")
                || requestURI.contains("/login")
                || requestURI.contains("/register")
                || requestURI.contains("/logout");
    }

    private boolean isAdminOperation(String requestURI) {
        return requestURI.contains("/add")
                || requestURI.contains("/edit")
                || requestURI.contains("/update")
                || requestURI.contains("/delete");
    }
}
