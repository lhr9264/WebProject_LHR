<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/infoEdit.css">
<title>정보수정</title>
<script>
    // 팝업 메시지를 표시하는 함수
    function showAlert(message) {
        alert(message);
    }

    // 서버로부터 받은 팝업 메시지를 확인하고, 메시지가 있으면 showAlert 함수를 호출하여 팝업을 표시합니다.
    window.onload = function() {
        <% String popupMessage = (String)request.getAttribute("popupMessage"); %>
        <% if (popupMessage != null && !popupMessage.isEmpty()) { %>
            showAlert("<%= popupMessage %>");
        <% } %>
    }
</script>
</head>
<body>
    <header id="header">
        <h1 class="logo">
            <a href="../membership/infoEdit.do">
                <span class="blind">로고</span>
            </a>
        </h1>
        <div class="gnb_right">
            <div class="info_update">
                <a href="../board/freeboardForm.jsp">게시판</a>
            </div>
            <div class="login_btn">
                <a href="../membership/logout.do">로그아웃</a>
            </div>
        </div>
    </header>
    <form action="../membership/infoEdit.do" method="post">
    <main id="container">
        <div class="main">
            <h2 class="logo">
            </h2>
        </div>
        <div class="info">
          <dl>
            <dt>
                <label>이름</label>
            </dt>
            <dd>
                <p><%= session.getAttribute("UserName") %></p>
            </dd>
            <dt class="dt2">
                <label>아이디</label>
            </dt>
            <dd class="dd2">
                <input type="text" name="newId" placeholder="<%= session.getAttribute("UserId") %>" />
                <span>새 아이디를 입력해주세요</span>
            </dd>
            <dt class="dt2">
                <label>비밀번호</label>
            </dt>
            <dd class="dd2">
                <input type="password" name="newPassword"/>
                <span>새 비밀번호를 입력해주세요</span>
            </dd>
          </dl>
        </div>
        <div class="btn_box">
            <button type="submit">변경 완료</button>
        </div>
    </main>
    </form>
</body>
</html>