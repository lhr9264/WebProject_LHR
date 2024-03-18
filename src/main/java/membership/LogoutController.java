package membership;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LogoutController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 세션을 무효화하여 로그아웃 처리
        HttpSession session = request.getSession();
        session.invalidate();

        // 로그아웃 후 로그인 페이지로 이동
        response.sendRedirect("../membership/login.do");
    }
}
