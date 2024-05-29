<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規会員登録</title>
</head>
<body>

    <form action="/RegistrationAction" method="post">
        <p>ログイン名：<input type="text" name="login" required></p>
        <p>パスワード：<input type="password" name="password" required></p>
        <p><input type="submit" value="新規会員登録"></p>
    </form>

</body>
</html>
