<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
    <h1>회원가입</h1>
    <form action="RegisterProcess.jsp" method="post">
        <div>
            <label for="id">아이디:</label>
            <input type="text" id="id" name="id">
        </div>
        <div>
            <label for="name">이름:</label>
            <input type="text" id="name" name="name">
        </div>
        <div>
            <label for="pass">비밀번호:</label>
            <input type="password" id="password" name="password">
        </div>
        <div>
            <button type="submit">가입하기</button>
        </div>
    </form>
</body>
</html>