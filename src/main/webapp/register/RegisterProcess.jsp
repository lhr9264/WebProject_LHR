<%@page import="membership.MemberDTO"%>
<%@page import="membership.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//회원가입 폼에서 전달된 데이터 받기
String id = request.getParameter("id");
String name = request.getParameter("name");
String password = request.getParameter("password");

//web.xml에 입력한 컨텍스트 초기화 파라미터를 읽어온다.
String oracleDriver = application.getInitParameter("OracleDriver");
String oracleURL = application.getInitParameter("OracleURL");
String oracleId = application.getInitParameter("OracleId");
String oraclePwd = application.getInitParameter("OraclePwd");

// MemberDAO 객체 생성
MemberDAO memberDAO = new MemberDAO(oracleDriver, oracleURL, oracleId, oraclePwd);

// 아이디 중복 체크
boolean isDuplicate = memberDAO.checkDuplicateId(id);

//중복 체크 결과에 따라 회원가입 처리
if (!isDuplicate) {
    // 회원 정보를 DTO에 설정
    MemberDTO member = new MemberDTO();
    member.setId(id);
    member.setName(name);
    member.setPass(password);

    // 회원가입 처리 수행
    boolean isSuccess = memberDAO.addMember(member);

    // 회원가입 결과에 따라 팝업 띄우기
    if (isSuccess) {
%>
<script>
    alert('회원 가입이 완료되었습니다.');
    window.location.href = '../login/LoginForm.jsp';
</script>
<%
    } else {
%>
<script>
    alert('빈칸이 있습니다. 모두 입력해주세요.');
    window.location.href = 'RegisterForm.jsp';
</script>
<%
    }
} else {
%>
<script>
    alert('이미 사용 중인 아이디입니다.');
    window.location.href = 'RegisterForm.jsp';
</script>
<%
}

// MemberDAO와 커넥션 자원 해제
memberDAO.close();
%>
