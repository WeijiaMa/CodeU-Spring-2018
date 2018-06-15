<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.User" %>
<%@ page import="codeu.model.store.basic.UserStore" %>

<!DOCTYPE html>
<html>
<head>
  <title>All Users</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>

  <nav>
    <a id="navTitle" href="/">CodeU Chat App</a>
    <a href="/conversations">Conversations</a>
    <% if(request.getSession().getAttribute("user") != null){ %>
      <a href="/allusers">All Users</a>
    <% } else{ %>
      <a href="/login">Login</a>
    <% } %>
    <a href="/about.jsp">About</a>
  </nav>

  <div id="container">

    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <h1>All registered users</h1>

    <%
    List<User> users =
      (List<User>) request.getAttribute("users");
    if(users == null || users.isEmpty()){
    %>
      <p>There are currently no registered users.</p>
    <%
    }
    else{
    %>
      <ul class="mdl-list">
    <%
      for(User user : users){
    %>
      <li><a href="/users/<%= user.getName() %>">
        <%= user.getName() %></a></li>
    <%
      }
    %>
      </ul>
    <%
    }
    %>
    <hr/>
  </div>
</body>
</html>
