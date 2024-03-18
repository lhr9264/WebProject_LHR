package membership;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class InfoEditController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GET 요청이 오면 정보 수정 폼을 보여줌
        request.getRequestDispatcher("../membership/InfoEditForm.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    // POST 요청 처리
	    // 요청에서 파라미터 가져오기
	    String newId = request.getParameter("newId");
	    String newPassword = request.getParameter("newPassword");
	    String userId = (String) request.getSession().getAttribute("UserId");
	    
	    // MemberDAO 인스턴스 생성
	    MemberDAO memberDAO = new MemberDAO();
	    
	    // 이미 사용 중인 아이디인지 확인
	    boolean isDuplicateId = memberDAO.checkDuplicateId(newId);
	    
	    if (isDuplicateId) {
	        // 이미 사용 중인 아이디일 경우 처리
	        request.setAttribute("popupMessage", "이미 사용 중인 아이디입니다.");
	        // 정보 수정 폼으로 다시 이동
	        request.getRequestDispatcher("../membership/InfoEditForm.jsp").forward(request, response);
	        return; // 아래 로직은 실행하지 않음
	    }
	    
	    // 새로운 아이디와 비밀번호가 비어 있는지 확인
	    if (newId != null && !newId.isEmpty() && newPassword != null && !newPassword.isEmpty()) {
	        // 회원 정보 수정
	        boolean success = memberDAO.updateMember(newId, newPassword, userId);
	        
	        if (success) {
	            // 정보 수정이 성공했을 때의 처리
	            // 성공 메시지를 설정하여 리다이렉트할 JSP 페이지로 전달
	            request.setAttribute("popupMessage", "정보가 성공적으로 수정되었습니다.");
	        } else {
	            // 정보 수정이 실패했을 때의 처리
	            // 실패 메시지를 설정하여 리다이렉트할 JSP 페이지로 전달
	            request.setAttribute("popupMessage", "정보 수정에 실패하였습니다.");
	        }
	    } else {
	        // 아이디나 비밀번호가 입력되지 않은 경우의 처리
	        // 에러 메시지를 설정하여 리다이렉트할 JSP 페이지로 전달
	        request.setAttribute("popupMessage", "아이디와 비밀번호를 모두 입력해주세요.");
	    }
	    
	    // 정보 수정 후에는 홈페이지로 리다이렉트
	    request.getSession().setAttribute("popupMessage", "정보가 성공적으로 수정되었습니다.");
	    response.sendRedirect("../membership/logout.do");
	}
}