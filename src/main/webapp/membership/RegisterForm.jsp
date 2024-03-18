<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/register.css">
<title>회원가입</title>
<script>
    window.onload = function() {
        const urlParams = new URLSearchParams(window.location.search);
        const error = urlParams.get('error');
        const success = urlParams.get('success');

        if (error === 'idEmpty') {
            alert('아이디를 입력해주세요.');
        } else if (error === 'passwordEmpty') {
            alert('비밀번호를 입력해주세요.');
        } else if (error === 'nameEmpty') {
	        alert('이름을 입력해주세요.');
        } else if (error === 'duplicateId') {
            alert('이미 사용 중인 아이디입니다.');
        } else if (success === 'true') {
            alert('회원가입이 완료되었습니다.');
        }
    }
</script>
</head>
<body>
    <div id="wrapper">
        <div class="header">
            <h1 class="logo">
                <a href="../">
                    <span class="blind">로고</span>
                </a>
            </h1>
            <div class="tab">
                <span>회원가입</span>
            </div>
        </div>
        <div class="contents">
            <div class="registerSec">
                <form action="../membership/register.do" method="post">
                    <div class="register_input">
                        <input type="text" id="id" name="id" placeholder="아이디 또는 이메일">
                    </div>
                    <div class="register_input">
                        <input type="text" id="name" name="name" placeholder="이름">
                    </div>
                    <div class="register_input">
                        <input type="password" id="password" name="password" placeholder="비밀번호">
                    </div>
                    <div>
                        <button type="submit">가입하기</button>
                    </div>
                    <div class="login_box">
                        <a href='../membership/login.do'>로그인</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>