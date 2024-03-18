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
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String password = request.getParameter("password");

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

        MemberDTO member = new MemberDTO();
        member.setId(id);
        member.setName(name);
        member.setPass(password);

        boolean result = memberDAO.addMember(member);

        if (result) {
            response.sendRedirect("../membership/login.do?success=true");
        } else {
            response.sendRedirect("../membership/register.do?error=duplicateId");
        }
    }
}
