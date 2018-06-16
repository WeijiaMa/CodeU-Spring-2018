package codeu.controller;

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {

    /** Store class that gives access to Users. */
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

    /**
     * Sets the UserStore used by this servlet. This function provides a common setup method for use
     * by the test framework or the servlet's init() function.
     */
    void setUserStore(UserStore userStore) {
        this.userStore = userStore;
    }

    /**
     * This function fires when a user requests the /allusers URL. It forwards the request to allusers.jsp.
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<User> users = userStore.getAllUsers();
        request.setAttribute("users", users);
        request.getRequestDispatcher("/WEB-INF/view/allusers.jsp").forward(request, response);
    }
}
