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
import codeu.controller.keywordfreq;

/** Servlet class responsible for the login page.
 * @author: Konce Quispe */
public class ProfileServlet extends HttpServlet {

    private UserStore userStore;
    /**
     * Set up state for handling profile-related requests. This method is only called when
     * running in a server, not when running in a test.
     */
    @Override
    public void init() throws ServletException {
        super.init();
        setUserStore(UserStore.getInstance());
    }

    void setUserStore(UserStore userStore) {
      this.userStore = userStore;
    }
    /**
     * This function fires when a user requests the /users/ URL. It gets the user from the
     * URL and forwards the request to profile.jsp.
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String bf = "";
        String user = requestUrl.substring("/users/".length());
        int kwmatching = 0;
        List<User> users = userStore.getAllUsers();

        for (User usera: users) {
            String bioa = usera.getBio();
            int kwmatched = Keywordfreq.kwcmp(user.getbio(), Keywordfreq.str2kw(bioa));
            if(kwmatched > kwmatching) {
                bf = usera.getName();
            }
        }

        request.setAttribute("bf", bf);
        String requestUrl = request.getRequestURI();
        request.setAttribute("user", user);
        request.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(request, response);
    }

    /**
     * Currently does nothing. TODO: <MVP will utilize this when the user submits their bio through
     * text box, and the information will be stored in the model/store/basic directory so their bio
     * can appear on their profile page.>
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.sendRedirect("/users");
    }
}
