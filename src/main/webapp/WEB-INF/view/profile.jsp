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
    <% if(request.getSession().getAttribute("user") != null){ %>
      <a>Hello <%= request.getSession().getAttribute("user") %>!</a>
    <% } else{ %>
      <a href="/login">Login</a>
    <% } %>
    <a href="/about.jsp">About</a>
  </nav>

  <div id="container">
    <h1><%= request.getAttribute("user") %>s profile page</h1>
    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>
    <%
        if(request.getAttribute("user").equals(request.getSession().getAttribute("user"))){
    %>
          <p>Update your bio:</p>
          <form action="profile.jsp" method="POST">
            <textarea name="paragraph_text" cols="85" rows="8"></textarea>
          </index></form>
        <% }
        else{ %>
          <p>About <%= request.getAttribute("user") %>:</p>
        <% } %>

  </div>
</body>
</html>
