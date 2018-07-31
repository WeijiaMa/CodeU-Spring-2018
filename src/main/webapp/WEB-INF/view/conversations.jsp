<%--
  Copyright 2017 Google Inc.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
--%>
<%@ page import="java.util.List" %>
<%@ page import="codeu.model.data.Conversation" %>
<%@ page import="codeu.model.data.User" %>

<!DOCTYPE html>
<html>
<head>
  <title>Conversations</title>
  <link rel="stylesheet" href="/css/main.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script type="text/javascript" src="/js/conversations.js"></script>
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

    <% if(request.getAttribute("error") != null){ %>
        <h2 style="color:red"><%= request.getAttribute("error") %></h2>
    <% } %>

    <% if(request.getAttribute("username") != null){ %>
      <h1>New Conversation</h1>
      <form action="/conversations" method="POST">
        <div class="form-group">
          <label class="form-control-label">Title:</label>
          <input type="text" name="conversationTitle" id="conversationTitle">
        </div>
        <div class="form-group" id="typeRadio">
          <label class="form-control-label">Type:</label>
          <input type="radio" name="conversationType" value="public" id="typePublic" checked> Public
          <input type="radio" name="conversationType" value="private"> Private
        </div>
        <div class="hidden" id="conversationParticipants">
          <div class="form-group">
            <label class="form-control-label">Participant:</label>
            <input type="input" name="conversationParticipant" id="conversationParticipant">
          </div>
          <button id="postParticipantBtn" type="button">Add</button>

          <%
            List<User> participants =
                (List<User>) request.getAttribute("participants");
            if(participants != null){
          %>
          <ul class="mdl-list">
            <%
              for(User p : participants){
            %>
            <li>
              <%= p.getName() %>
            </li>
            <% } %>
          </ul>
          <% } %>
        </div>

        <button type="submit">Create</button>
      </form>

      <hr/>
    <% } %>

    <h1>Conversations</h1>

    <%
    List<Conversation> conversations =
      (List<Conversation>) request.getAttribute("conversations");
    if(conversations == null || conversations.isEmpty()){
    %>
      <p>Create a conversation to get started.</p>
    <%
    }
    else{
    %>
      <ul class="mdl-list">
    <%
      for(Conversation conversation : conversations){
    %>
      <li><a href="/chat/<%= conversation.getTitle() %>">
        <%= conversation.getTitle() %></a></li>
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
