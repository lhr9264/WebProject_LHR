<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/freeboard.css">
<title>자유게시판</title>
</head>
<body>
	<%
	if (session.getAttribute("member") == null) {
	%>
		<script>
			alert("로그인 후 이용해주세요");
			location.href= "../membership/login.do;"
		</script>
	<%
	} 
	%>
    <header id="header">
        <h1 class="logo">
            <a href="../board/freeboard.do">
                <span class="blind">로고</span>
            </a>
        </h1>
        <div class="gnb_right">
            <div class="info_update">
                <a href="../membership/infoEdit.do">정보수정</a>
            </div>
            <div class="login_btn">
                <a href="../membership/logout.do">로그아웃</a>
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
                        <li><a href="../board/freeboard.do">자유게시판</a></li>
                        <li><a href="#">Q&A게시판</a></li>
                        <li><a href="#">자료실게시판</a></li>
                    </ul>
                </div>
            </div>
            <div class="board">
                <div class="board_list">
                    <div class="header">
                        <div class="tit">자유게시판</div>
                        <form method="get">
                        <div class="utils">
                            <div class="search_from">
                                <div class="num">총 100개</div>
                            </div>
                            <div class="select_wrap">
                                <select name="selectField" id="slcSearchType">
                                    <option value="title">
                                        제목
                                    </option>
                                    <option value="writer">
                                        작성자
                                    </option>
                                </select>
                            </div>
                            <div class="form_wrap">
                                <input name="searcWord" type="text" id="searchInput" placeholder="검색" />
                                <button type="submit">
                                    <span class="search_icon"></span>
                                </button>
                            </div>
                        </div>
                        </form>
                    </div>
                    <div class="content_box">
                        <div class="list_wrap">
                            <table>
                                <tr>
                                    <th>번호</th>
                                    <th>제목</th>
                                    <th>작성자</th>
                                    <th>추천</th>
                                    <th>조회수</th>
                                    <th>작성일</th>
                                </tr>
<c:choose>
	<c:when test="${ empty boardLists }">
		<tr>
			<td class=test >등록된 게시물이 없습니다.</td>
		</tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${ boardLists }" var="row" varStatus="loop">
		<tr>
			<td class="a">
				${ map.totalCount - (((map.pageNum-1) * map.pageSize)
            		+ loop.index)}
			</td>
			 <td>
                <a href="../board/view.do?idx=${ row.idx }">
                	${ row.title }</a>
            </td> 
            <td>${ row.name }</td>
            <td>${ row.thumb }</td>
            <td>${ row.visitcount }</td>
            <td>${ row.postdate }</td>
		</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
                             </table>
                        </div>
                    </div>
                     <div class="footer">
               			 <div class="btn_area">
                   			 <a class="write_btn" href="../board/write.jsp">
		                        <span class="write_icon"></span>
		                        <span>게시물 작성하기</span>
                   			 </a>
                </div>
            </div>
                </div>
            </div>
        </div>
    </main>
</body>
</html>