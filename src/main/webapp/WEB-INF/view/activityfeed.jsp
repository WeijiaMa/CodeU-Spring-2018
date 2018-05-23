<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="codeu.model.data.Message" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.ZoneId" %>


<%
List<Message> messages = (List<Message>) request.getAttribute("messages");
List<Conversation> conversations = (List<Conversation>) request.getAttribute("conversations");
%>

<!DOCTYPE html>
<html>
<head>
  <title>CodeU Chat App</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>
  <nav>
    <a id="navTitle" href="/">CodeU Chat App</a>
    <a href="/conversations">Conversations</a>
    <% if(request.getSession().getAttribute("user") != null){ %>
      <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
    <% } else{ %>
      <a href="/login">Login</a>
    <% } %>
    <a href="/about.jsp">About</a>
    <a href="/activity"> Activity Feed </a>
  </nav>

  <div id="container">
    <div
      style="text-align: center; margin-top: 50px;">

      <h1>CodeU Activity Feed</h1>
    </div>
    <p>This is all the message activity on the site:</p>

    <div id= "feed">
      <% if(messages == null || messages.isEmpty()) { %>
      <p> <a href="/login">Login</a> to start chatting! there are no messages yet! </p>
      <%
      } else {
      %>

      <ul>
    <%
    for (Message message : messages) {
      String author = UserStore.getInstance().getUser(message.getAuthorId()).getName();
      //String convo = UserStore.getInstance().getUser(message.getConversationId()).getTitle(); //beyond mvp
      LocalDateTime ldt = LocalDateTime.ofInstant(message.getCreationTime(), ZoneId.systemDefault()); //from oracle docs
      %> <li>
      <%= ldt.getMonth()%>, <%= ldt.getDayOfMonth()%> <%= ldt.getYear()%>
       <%= ldt.getHour()%>:<%= ldt.getMinute()%> in chat: <%-- <%= convo %> --%>
      <strong><%= author %>:</strong> <%= message.getContent() %>
      </li>
      <% } %>
    </ul>
    <%
    }
    %>
  </div>
  </div>
</body>
</html>
