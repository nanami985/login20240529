<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
</head>
<body>

    <form action="/Login/LoginAction" method="post">
        <p>ログイン名：<input type="text" name="login" value="<%= (request.getAttribute("login") != null) ? request.getAttribute("login") : "" %>" required></p>
        <p>パスワード：<input type="password" name="password" required></p>
        <p><input type="submit" value="ログイン"></p>
    </form>
    
    <% 
        String error = (String)request.getAttribute("error");
        if(error != null) {
    %>
        <p style="color: red;"><%= error %></p>
    <% } %>
    
    <a href="registration-in.jsp">会員登録が済んでいない方はこちら</a>
    

</body>
</html>
