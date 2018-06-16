package codeu.controller;

import javax.servlet.http.HttpServlet;

import codeu.model.data.Conversation;
import codeu.model.data.User;
import codeu.model.store.basic.UserStore;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Servlet class responsible for the login page.
 *
 * @author: Konce Quispe
 */
public class ProfileServlet extends HttpServlet {

    /**
     * Set up state for handling profile-related requests. This method is only called when
     * running in a server, not when running in a test.
     */
    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     * This function fires when a user requests the /users/ URL. It gets the user from the
     * URL and forwards the request to profile.jsp.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String requestUrl = request.getRequestURI();
        String user = requestUrl.substring("/users/".length());
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
    }

    /**
     * Gets the username from the session and finds the corresponding user in the UserStore.
     * Accesses the current bio for this user and updates it with the information from the submission
     * form. Updates the user and redirects back to the user's profile page.
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String username = (String) request.getSession().getAttribute("user");
        User user = UserStore.getInstance().getUser(username);
        String bio = request.getParameter("bio");
        user.setBio(bio);
        UserStore.getInstance().updateUser(user);
        response.sendRedirect("/users/" + username);
    }
}
