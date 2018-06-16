<%@ page import="codeu.model.data.User" %>
<%@ page import="codeu.model.store.basic.UserStore" %>

<!DOCTYPE html>
<html>
<head>
  <title>Profile</title>
  <link rel="stylesheet" href="/css/main.css">
</head>
<body>

  <nav>
    <a id="navTitle" href="/">CodeU Chat App</a>
    <a href="/conversations">Conversations</a>
    <%
      if(request.getSession().getAttribute("user") != null){ %>
      <a href="/allusers">All Users</a>
    <% } else{ %>
      <a href="/login">Login</a>
    <% } %>
    <a href="/about.jsp">About</a>
  </nav>

  <div id="container">
    <h1><%= request.getAttribute("user") %>'s Profile Page</h1>
    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>
    <%
        String username = (String) request.getSession().getAttribute("user");
        User user = UserStore.getInstance().getUser(username);
        String bio = request.getParameter("bio");
        if(request.getAttribute("user").equals(username)){
    %>
          <h3>About <%= username %>:</h3>
          <%
          if(bio != null) { %>
            <p><%= bio %></p>
          <% }
          else{ %>
            <p>You have not added a bio.</p> <%
              } %>
          <br/>
          <form method="POST">
                <label for="bio">Update your bio: </label>
                <br/>
                <input type="text" name="bio" id="bio">
                <br/>
                <button type="submit">Submit</button>
          </form>
        <% }
        else{ %>
          <h3>About <%= request.getAttribute("user") %>:</h3>
          <%
          if(bio != null) { %>
            <p><%= bio %></p> <%
         }
         else{
         %> <p>User has not added a bio.</p> <%
         }
       }
       %>

  </div>
</body>
</html>
