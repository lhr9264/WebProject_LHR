package membership;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterController extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
	private MemberDAO memberDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        memberDAO = new MemberDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("../membership/RegisterForm.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 폼에서 전송된 회원 정보를 가져옵니다.
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        // 아이디나 비밀번호가 비어있는 경우 처리
        if (id.isEmpty()) {
            response.sendRedirect("../membership/register.do?error=idEmpty");
            return;
        }
        if (name.isEmpty()) {
            response.sendRedirect("../membership/register.do?error=nameEmpty");
            return;
        }
        if (password.isEmpty()) {
            response.sendRedirect("../membership/register.do?error=passwordEmpty");
            return;
        }

        // 가져온 정보로 MemberDTO 객체를 생성합니다.
        MemberDTO member = new MemberDTO();
        member.setId(id);
        member.setName(name);
        member.setPass(password);

        // MemberDAO를 사용하여 회원 추가를 시도합니다.
        boolean result = memberDAO.addMember(member);

        // 회원 추가 결과에 따라 적절한 응답을 전송합니다.
        if (result) {
            // 회원 가입 성공 시
            response.sendRedirect("../membership/login.do?success=true");
        } else {
            // 이미 사용중인 아이디인 경우
            response.sendRedirect("../membership/register.do?error=duplicateId");
        }
    }
}
