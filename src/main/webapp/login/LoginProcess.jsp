<%@page import="membership.MemberDTO"%>
<%@page import="membership.MemberDAO"%>
<%@ page import="java.io.*,java.util.*,java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
//로그인 폼에서 입력한 값을 받는다.
String id = request.getParameter("id");
String password = request.getParameter("password");

//web.xml에 입력한 컨텍스트 초기화 파라미터를 읽어온다.
String oracleDriver = application.getInitParameter("OracleDriver");
String oracleURL = application.getInitParameter("OracleURL");
String oracleId = application.getInitParameter("OracleId");
String oraclePwd = application.getInitParameter("OraclePwd");

//위 정보를 통해 DAO 인스턴스를 생성하고 오라클에 연결한다.
MemberDAO dao = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);
MemberDTO memberDTO = dao.getMemberDTO(id, password);
//자원반납
dao.close();

//로그인 결과에 따라 처리
if (memberDTO.getId() != null) {
 // 로그인 성공 시 세션에 사용자 정보 저장
 	session.setAttribute("UserId", memberDTO.getId());
	session.setAttribute("UserName", memberDTO.getName());
    response.sendRedirect("../freeboard/freeboardForm.jsp"); // 로그인 성공 시 이동할 페이지
	} 

else {
%>
<script>
	alert('아이디 또는 비밀번호가 일치하지 않습니다.');
	window.location.href = 'LoginForm.jsp';
</script>
<%
	}
%>