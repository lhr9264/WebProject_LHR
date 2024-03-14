<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
//세션 삭제
session.invalidate();
//로그아웃 처리후 로그인 페이지로 '이동'한다.
response.sendRedirect("LoginForm.jsp");
%>
    