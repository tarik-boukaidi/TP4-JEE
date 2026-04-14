package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import dao.UserImpl;
import models.User;

public class RegisterServlet extends HttpServlet {
    private UserDAO userDAO = new UserImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        if (username == null || username.trim().isEmpty() || 
            password == null || password.trim().isEmpty() ||
            email == null || email.trim().isEmpty()) {
            request.setAttribute("error", "Tous les champs sont obligatoires");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }
        
        if (userDAO.usernameExists(username)) {
            request.setAttribute("error", "Cet utilisateur existe déjà");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }
        
        if (userDAO.emailExists(email)) {
            request.setAttribute("error", "Cet email est déjà utilisé");
            request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
            return;
        }
        
        User newUser = new User(username, password, email, "USER");
        userDAO.save(newUser);
        
        request.setAttribute("success", "Inscription réussie! Vous pouvez maintenant vous connecter");
        request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
    }
}
