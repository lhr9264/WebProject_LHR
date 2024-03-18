package membership;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class InfoEditController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../membership/InfoEditForm.jsp").forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String newId = request.getParameter("newId");
	    String newPassword = request.getParameter("newPassword");
	    String userId = (String) request.getSession().getAttribute("UserId");
	    
	    MemberDAO memberDAO = new MemberDAO();
	    
	    boolean isDuplicateId = memberDAO.checkDuplicateId(newId);
	    
	    if (isDuplicateId) {
	        request.setAttribute("popupMessage", "이미 사용 중인 아이디입니다.");
	        request.getRequestDispatcher("../membership/InfoEditForm.jsp").forward(request, response);
	        return; 
	    }
	    
	    if (newId != null && !newId.isEmpty() && newPassword != null && !newPassword.isEmpty()) {
	        boolean success = memberDAO.updateMember(newId, newPassword, userId);
	        
	        if (success) {
	            request.setAttribute("popupMessage", "정보가 성공적으로 수정되었습니다.");
	        } else {
	            request.setAttribute("popupMessage", "정보 수정에 실패하였습니다.");
	        }
	    } else {
	        request.setAttribute("popupMessage", "아이디와 비밀번호를 모두 입력해주세요.");
	    }
	    
	    request.getSession().setAttribute("popupMessage", "정보가 성공적으로 수정되었습니다.");
	    response.sendRedirect("../membership/logout.do");
	}
}