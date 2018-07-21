package codeu.controller;

import codeu.model.data.Conversation;
import codeu.model.data.User;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.UserStore;
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
    private ConversationServlet conversationServlet;
    private HttpServletRequest mockRequest;
    private HttpSession mockSession;
    private HttpServletResponse mockResponse;
    private RequestDispatcher mockRequestDispatcher;
    private ConversationStore mockConversationStore;
    private UserStore mockUserStore;

    @Before
    public void setup() {
        profileServlet = new ProfileServlet();
        conversationServlet = new ConversationServlet();
        mockConversationStore = Mockito.mock(ConversationStore.class);
        conversationServlet.setConversationStore(mockConversationStore);
        mockUserStore = Mockito.mock(UserStore.class);
        conversationServlet.setUserStore(mockUserStore);
        mockRequest = Mockito.mock(HttpServletRequest.class);
        mockSession = Mockito.mock(HttpSession.class);
        Mockito.when(mockRequest.getSession()).thenReturn(mockSession);
        mockResponse = Mockito.mock(HttpServletResponse.class);
        mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
        Mockito.when(mockRequest.getRequestDispatcher("/WEB-INF/view/profile.jsp"))
                .thenReturn(mockRequestDispatcher);
    }

    @Test
    public void testDoGet() throws IOException, ServletException {
        /*
        User fakeUser =
                new User(
                        UUID.randomUUID(),
                        "test_username",
                        "$2a$10$eDhncK/4cNH2KE.Y51AWpeL8/5znNBQLuAFlyJpSYNODR/SJQ/Fg6",
                        Instant.now(), "");
        Mockito.when(mockUserStore.getUser("test_username")).thenReturn(fakeUser);

        Mockito.when(mockRequest.getRequestURI()).thenReturn("/users/test_profile");
        profileServlet.doGet(mockRequest, mockResponse);
        String fakeUser2 = mockRequest.getRequestURI().substring("/users/".length());
        Mockito.verify(mockRequest).setAttribute("user", fakeUser2);

        List<Conversation> fakeConversationList = new ArrayList<>();
        List<UUID> testParticipants = new ArrayList<>();
        testParticipants.add(fakeUser.getId());
        fakeConversationList.add(
                new Conversation(UUID.randomUUID(), fakeUser.getId(), "test_conversation_private", Instant.now(), true, testParticipants));
        fakeConversationList.add(
                new Conversation(UUID.randomUUID(), UUID.randomUUID(), "test_conversation_public", Instant.now(), false, null));
        Mockito.when(mockConversationStore.getAvailableConversations(fakeUser)).thenReturn(fakeConversationList);
        Mockito.verify(mockRequest).setAttribute("conversations", fakeConversationList);
        Mockito.verify(mockRequestDispatcher).forward(mockRequest, mockResponse);
        */
    }


}
