package membership;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
	private MemberDAO memberDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        memberDAO = new MemberDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.getRequestDispatcher("../membership/LoginForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        String password = request.getParameter("password");

        // 아이디 또는 비밀번호가 빈 칸인 경우
        if (id.isEmpty() || password.isEmpty()) {
            // 비어있는 필드에 따라 다른 팝업을 표시
            if (id.isEmpty()) {
                response.sendRedirect("../membership/login.do?error=emptyId");
            } else {
                response.sendRedirect("../membership/login.do?error=emptyPassword");
            }
            return;
        }

        // MemberDAO를 사용하여 사용자 정보를 가져옵니다.
        MemberDTO member = memberDAO.getMemberDTO(id, password);

        // 가져온 회원 정보가 null이 아니면 로그인 성공으로 간주합니다.
        if (member != null && member.getId() != null) {
            // 세션에 사용자 정보를 저장합니다.
            HttpSession session = request.getSession();
            session.setAttribute("member", member);
        	session.setAttribute("UserId", member.getId());
        	session.setAttribute("UserName", member.getName());

            // 로그인 성공 시 freeboard.do로 이동합니다.
            response.sendRedirect("../board/freeboardForm.jsp");
        } else {
            // 로그인 실패 시 로그인 페이지로 이동하고 실패 메시지를 전달합니다.
            response.sendRedirect("../membership/login.do?error=incorrectCredentials");
        }
    }

}
