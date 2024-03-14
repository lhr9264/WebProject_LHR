<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/freeboard.css">
<title>자유게시판</title>
</head>
<body>
	<%
	if (session.getAttribute("UserId") == null) {
	%>
		<script>
			alert("로그인 후 이용해주세요");
			location.href= "../login/LoginForm.jsp";
		</script>
	<%
	} else {
	%>
		<script>
			alert("<%=session.getAttribute("UserName") %>회원님, 로그인하셨습니다.");
		</script>
	<%
	}
	%>
    <header id="header">
        <h1 class="logo">
            <a href="/새로고침">
                <span class="blind">로고</span>
            </a>
        </h1>
        <div class="gnb_right">
            <div class="info_update">
                <a href="정보수정">정보수정</a>
            </div>
            <div class="login_btn">
                <a href="../login/LogoutProcess.jsp">로그아웃</a>
            </div>
        </div>
    </header>
    <main id="container">
        <div class="board">
            <div class="header">
                <div class="tit">자유게시판</div>
            </div>
        </div>
    </main>
</body>
</html>