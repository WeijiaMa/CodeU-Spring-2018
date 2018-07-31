<%@ page import="codeu.model.data.User" %>
<%@ page import="codeu.model.store.basic.UserStore" %>
<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Conversation" %>

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
    <% if(request.getAttribute("username") != null){ %>
          <a href="/users/<%=request.getAttribute("username")%>">Hello <%= request.getAttribute("username") %>!</a>
    <% } else{ %>
      <a href="/login">Login</a>
    <% } %>
    <a href="/allusers">All Users</a>
    <a href="/about.jsp">About</a>
    <% if(request.getSession().getAttribute("user") != null){ %>
       <a href="/logout">Logout</a>
      <% } %>
  </nav>

  <div id="container">
    <h1><%= request.getAttribute("user") %>'s Profile Page</h1>
    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>
    <%
    String username = (String)request.getSession().getAttribute("user");
    User user = UserStore.getInstance().getUser(username);
    String bio = user.getBio();

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

            <h3>Your public conversations:</h3>

            <%
            List<Conversation> publicConvo = (List<Conversation>)request.getAttribute("publicConvo");
            if(publicConvo == null || publicConvo.isEmpty()){
                %>
                 <p>You are not part of any public conversations.</p>
                 <%
            }
            else{
                for(Conversation conversation : publicConvo){
                    %>
                    <li><a href="/chat/<%= conversation.getTitle() %>">
                    <%= conversation.getTitle() %></a></li>
                    <%
                }
            } %>

            <h3>Your private conversations:</h3>

             <%
             List<Conversation> privateConvo = (List<Conversation>)request.getAttribute("privateConvo");
             if(privateConvo == null || privateConvo.isEmpty()){
                %> <p>You are not part of any private conversations.</p>
             <% }
             else{
                for(Conversation conversation : privateConvo){
                   %>
                   <li><a href="/chat/<%= conversation.getTitle() %>">
                   <%= conversation.getTitle() %></a></li>
                   <%
                   }
             }

    }
    else{ %>
      <p>This is the profile page for: <%= request.getAttribute("user") %>!</p>
      <h3>About <%= request.getAttribute("user") %>:</h3>
          <%
          username = (String)request.getAttribute("user");
          user = UserStore.getInstance().getUser(username);
          bio = user.getBio();
          if(bio != null) { %>
              <p><%= bio %></p> <%
               }
               else{
               %> <p>User has not added a bio.</p>
               <%}
  }
 %>

</div>
</body>
</html>
