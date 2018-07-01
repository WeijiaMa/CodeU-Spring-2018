<!DOCTYPE html>
<html>
<head>
  <title>Login</title>
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
    <% if(request.getSession().getAttribute("user") != null){ %>
       <a href="/logout">Logout</a>
      <% } %>
  </nav>


    <div id="container">
    <h1>Logout</h1>

    <% if(request.getSession().getAttribute("user") == null) {%>
      <p>Users can login <a href="/register">here!</a>.</p>
    <% }  else{ %>
    <p> Are you sure you want to logout? </p>
    <form action ="/logout" method = "POST">
      <button type="submit">Yes</button>
    </form>

    <a href ="/">
    <button>No</button> </a>
  </div>
    <% } %>
