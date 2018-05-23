package codeu.controller;

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;

import codeu.model.data.Message;
import codeu.model.store.basic.MessageStore;

import codeu.model.data.Conversation;
import codeu.model.store.basic.ConversationStore;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.servlet.ServletException;

public class ActivityFeedServlet extends HttpServlet {
  private UserStore userStore;

  private ConversationStore conversationStore;

  private MessageStore messageStore;


  @Override
  public void init() throws ServletException {
    super.init();
    setUserStore(UserStore.getInstance());
    setConversationStore(ConversationStore.getInstance());
    setMessageStore(MessageStore.getInstance());
  }

  void setUserStore(UserStore userStore) {
    this.userStore = userStore;
  }

  void setConversationStore(ConversationStore conversationStore) {
    this.conversationStore = conversationStore;
  }

  void setMessageStore(MessageStore messageStore) {
    this.messageStore = messageStore;
  }

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
      List<Message> messages = messageStore.getAllMessages();
      List<Conversation> conversations = conversationStore.getAllConversations();
      request.setAttribute("messages", messages);
      request.setAttribute("conversations", conversations);
      request.getRequestDispatcher("WEB-INF/view/activityfeed.jsp").forward(request, response);
  }


}
