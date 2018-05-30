package codeu.controller;

import codeu.model.data.User;
import codeu.model.store.basic.UserStore;

import codeu.model.data.Message;
import codeu.model.store.basic.MessageStore;

import codeu.model.data.Conversation;
import codeu.model.store.basic.ConversationStore;

import java.util.ArrayList;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.Locale;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.Month;
import java.time.format.TextStyle;

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
    throws IOException, ServletException, NullPointerException {
      List<Message> messages = messageStore.getAllMessages();
      List<Conversation> conversations = conversationStore.getAllConversations();
      List<User> users = userStore.getAllUsers();
      List<String> feeds = new ArrayList();
      List<String> convo2Feed = new ArrayList();
      List<String> user2Feed = new ArrayList();

      for (Message message: messages) {
        try {
        String author = UserStore.getInstance().getUser(message.getAuthorId()).getName();

        LocalDateTime ldt = LocalDateTime.ofInstant(message.getCreationTime(), ZoneId.systemDefault()); //from oracle docs
        String month = ldt.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        int day = ldt.getDayOfMonth();
        int year = ldt.getYear();
        int hour = ldt.getHour();
        int minute = ldt.getMinute();
        String date = String.format("%s %d, %d, %d:%d : ",month, day, year, hour, minute);

        feeds.add("<strong>" + date + author  + ": </strong>" + message.getContent());
      }
     catch (NullPointerException e) {
      System.out.println("caught NullPointerException");
    }
  }
      for(Conversation conversation: conversations) {
        LocalDateTime ldt = LocalDateTime.ofInstant(conversation.getCreationTime(), ZoneId.systemDefault()); //from oracle docs
        String month = ldt.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        int day = ldt.getDayOfMonth();
        int year = ldt.getYear();
        int hour = ldt.getHour();
        int minute = ldt.getMinute();
        String date = String.format("%s %d, %d: %d:%d : ",month, day, year, hour, minute);


        String author = UserStore.getInstance().getUser(conversation.getOwnerId()).getName();
        String title = conversation.getTitle();
        convo2Feed.add("<strong>" + date + author + "</strong>" + " just created a new chat! go talk to them at " + title);
      }

      for (User user: users) {
        LocalDateTime ldt = LocalDateTime.ofInstant(user.getCreationTime(), ZoneId.systemDefault()); //from oracle docs
        String month = ldt.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
        int day = ldt.getDayOfMonth();
        int year = ldt.getYear();
        int hour = ldt.getHour();
        int minute = ldt.getMinute();
        String date = String.format("%s %d, %d, %d:%d : ",month, day, year, hour, minute);


        String author = user.getName();
        user2Feed.add("<strong>" + date + author + "</strong>" + " just joined chat! Welcome them!");
      }
      request.setAttribute("messages", messages);
      request.setAttribute("conversations", conversations);
      request.setAttribute("feeds", feeds);
      request.setAttribute("convo2Feed", convo2Feed);
      request.setAttribute("user2Feed", user2Feed);
      request.getRequestDispatcher("WEB-INF/view/activityfeed.jsp").forward(request, response);
  }


}
