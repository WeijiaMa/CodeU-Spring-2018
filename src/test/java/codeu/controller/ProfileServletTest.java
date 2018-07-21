package codeu.controller;

import codeu.model.data.Conversation;
import codeu.model.store.basic.ConversationStore;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class ProfileServletTest {

    private ProfileServlet profileServlet;
    private HttpServletRequest mockRequest;
    private HttpSession mockSession;
    private HttpServletResponse mockResponse;
    private RequestDispatcher mockRequestDispatcher;

    @Before
    public void setup() {
        profileServlet = new ProfileServlet();
        mockRequest = Mockito.mock(HttpServletRequest.class);
        mockSession = Mockito.mock(HttpSession.class);
        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
        mockResponse = Mockito.mock(HttpServletResponse.class);
        mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(mockRequest.getRequestDispatcher("/WEB-INF/view/profile.jsp"))
                .thenReturn(mockRequestDispatcher);
    }
/*
    @Test
    public void testDoGet() throws IOException, ServletException {
        Mockito.when(mockRequest.getRequestURI()).thenReturn("/users/test_profile");
        profileServlet.doGet(mockRequest, mockResponse);
        String fakeUser = mockRequest.getRequestURI().substring("/users/".length());
        Mockito.verify(mockRequest).setAttribute("user", fakeUser);
        Mockito.verify(mockRequestDispatcher).forward(mockRequest, mockResponse);
    }
*/

}
