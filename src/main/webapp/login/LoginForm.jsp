<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/login.css">
<title>Login</title>
</head>
<body>
    <div id="wrapper">
        <div class="header">
            <h1 class="logo">
                <a href="로그인페이지">
                    <span class="blind">로고</span>
                </a>
            </h1>
            <div class="tab">
                <span>로그인</span>
            </div>
        </div>
        <div class="contents">
            <div class="contents_inner">
                <form action="LoginProcess.jsp" method="post">
                    <div class="input_box">
                        <input type="text" id="id" name="id" placeholder="아이디 또는 이메일">
                    </div>
                    <div class="input_box">
                        <input type="password" id="password" name="password" placeholder="비밀번호">
                    </div>
                    <div>
                        <button type="submit">넥슨ID(이메일) 로그인</button>
                    </div>
                     <div class="login_box">
                        <a href="../register/RegisterForm.jsp">회원가입</a>
                        <a href="/">넥슨ID 찾기</a>
                        <a href="/">비밀번호 찾기</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>