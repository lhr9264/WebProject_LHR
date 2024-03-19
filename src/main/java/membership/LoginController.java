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

        if (id.isEmpty() || password.isEmpty()) {
            if (id.isEmpty()) {
                response.sendRedirect("../membership/login.do?error=emptyId");
            } else {
                response.sendRedirect("../membership/login.do?error=emptyPassword");
            }
            return;
        }

        MemberDTO member = memberDAO.getMemberDTO(id, password);

        if (member != null && member.getId() != null) {
            HttpSession session = request.getSession();
            session.setAttribute("member", member);
        	session.setAttribute("UserId", member.getId());
        	session.setAttribute("UserName", member.getName());

            response.sendRedirect("../board/freeboard.do");
        } else {
            response.sendRedirect("../membership/login.do?error=incorrectCredentials");
        }
    }

}
