<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/login.css">
<title>로그인</title>
<script>
    function showPopup() {
        const urlParams = new URLSearchParams(window.location.search);
        const error = urlParams.get('error');
        if (error === 'emptyId') {
            alert('아이디를 입력해주세요.');
        } else if (error === 'emptyPassword') {
            alert('비밀번호를 입력해주세요.');
        } else if (error === 'incorrectCredentials') {
            alert('아이디 또는 비밀번호가 올바르지 않습니다.');
        }
    }
    window.onload = showPopup;
</script>
</head>
<body>
    <div id="wrapper">
        <div class="header">
            <h1 class="logo">
                <a href="../membership/login.do">
                    <span class="blind">로고</span>
                </a>
            </h1>
            <div class="tab">
                <span>로그인</span>
            </div>
        </div>
        <div class="contents">
            <div class="contents_inner">
                <form action="../membership/login.do" method="post">
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
                        <a href="../membership/register.do">회원가입</a>
                        <a href="/">넥슨ID 찾기</a>
                        <a href="/">비밀번호 찾기</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>