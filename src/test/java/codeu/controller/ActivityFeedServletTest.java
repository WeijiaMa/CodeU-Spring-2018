package codeu.controller;

import codeu.model.data.Conversation;
import codeu.model.data.Message;
import codeu.model.data.User;
import codeu.model.store.basic.ConversationStore;
import codeu.model.store.basic.MessageStore;
import codeu.model.store.basic.UserStore;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

public class ActivityFeedServletTest {

  private ActivityFeedServlet activityFeedServlet;
  private HttpServletRequest mockRequest;
  private HttpServletResponse mockResponse;
  private RequestDispatcher mockRequestDispatcher;
  private ConversationStore mockConversationStore;
  private MessageStore mockMessageStore;
  private UserStore mockUserStore;

  @Before
  public void setup() {
    activityFeedServlet = new ActivityFeedServlet();

    mockRequest = Mockito.mock(HttpServletRequest.class);

    mockResponse = Mockito.mock(HttpServletResponse.class);
    mockRequestDispatcher = Mockito.mock(RequestDispatcher.class);
    Mockito.when(mockRequest.getRequestDispatcher("/WEB-INF/view/activity.jsp"))
        .thenReturn(mockRequestDispatcher);

    mockConversationStore = Mockito.mock(ConversationStore.class);
    activityFeedServlet.setConversationStore(mockConversationStore);

    mockMessageStore = Mockito.mock(MessageStore.class);
    activityFeedServlet.setMessageStore(mockMessageStore);

    mockUserStore = Mockito.mock(UserStore.class);
    activityFeedServlet.setUserStore(mockUserStore);
  }

  @Test
  public void testDoGet() throws IOException, ServletException {
/*    List<Conversation> fakeConversationList = new ArrayList();
    fakeConversationList.add(new Conversation(UUID.randomUUID(), UUID.randomUUID(), "test_conversation", Instant.now()));
    Mockito.when(mockConversationStore.getAllConversations()).thenReturn(fakeConversationList);
*/
    UUID fakeConversationId = UUID.randomUUID();
    List<Message> fakeMessageList = new ArrayList<>();
    fakeMessageList.add(
        new Message(
            UUID.randomUUID(),
            fakeConversationId,
            UUID.randomUUID(),
            "test message",
            Instant.now()));
   Mockito.when(mockMessageStore.getAllMessages()).thenReturn(fakeMessageList);
/*
   List<String> fakeFeedsList = new ArrayList();  //for future modification when combine all lists to sort via ldt
   fakeFeedsList.add(
     "jackson", "Drew", "Konce", "Laya", "Weija", "Rachel", "Tiffany");
   //Mockito.when(mockUserStore.getAllUsers()).thenReturn(fakeFeedsList);

   List<String> fakeConvo2FeedList = new ArrayList();
   fakeConvo2FeedList.add(
     "jackson", "Drew", "Konce", "Laya", "Weija", "Rachel", "Tiffany");
   //Mockito.when(mockUserStore.getAllUsers()).thenReturn(fakeFeedsList);

   List<String> fakeuser2FeedList = new ArrayList();
   fakeuser2FeedList.add(
     "jackson", "Drew", "Konce", "Laya", "Weija", "Rachel", "Tiffany");
   //Mockito.when(mockUserStore.getAllUsers()).thenReturn(fakeFeedsList);
*/
    activityFeedServlet.doGet(mockRequest, mockResponse);

  //  Mockito.verify(mockRequest).setAttribute("conversations", fakeConversationList);
    Mockito.verify(mockRequest).setAttribute("messages", fakeMessageList);
  /*  Mockito.verify(mockRequest).setAttribute("feeds", fakeFeedsList);
    Mockito.verify(mockRequest).setAttribute("convo2Feed", fakeConvo2FeedList);
    Mockito.verify(mockRequest).setAttribute("user2Feed", fakeuser2FeedList); */
    Mockito.verify(mockRequestDispatcher).forward(mockRequest, mockResponse);
  }

}
