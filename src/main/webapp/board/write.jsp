<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/write.css"/>
<title>게시글 작성하기</title>
</head>
<body>
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
                <a href="/로그인">로그아웃</a>
            </div>
        </div>
    </header>
    <main id="container">
        <div class="community">
            <div class="board_header">
                <div class="tit">
                <h1>커뮤니티</h1>
                <span>커뮤니티에서 자유롭게 이야기 나누세요.</span>
                </div>
                <div class="tab_list">
                    <ul>
                        <li><a href="#">자유게시판</a></li>
                        <li><a href="#">Q&A게시판</a></li>
                        <li><a href="#">자료실게시판</a></li>
                    </ul>
                </div>
            </div>
            <div class="board">
                <div class="board_list">
                    <div class="header">
                        <div class="tit">글쓰기</div>
                    </div>
                </div>
                <div class="write_wrap">
                    <div class="editer">
                        <p class="title">
                            <Strong>제목</Strong>
                            <input type="text" placeholder="제목을 입력해 주세요!"/>
                        </p>
                    </div>
                    <div class="content">
                        <textarea name="content" placeholder="내용을 입력해주세요"></textarea>
                    </div>
                </div>
                <div class="btn_area">
                    <a href="작성하기">등록하기</a>
                    <a href="취소">취소</a>
                </div>
            </div>
        </div>
    </main>
</body>
</html>