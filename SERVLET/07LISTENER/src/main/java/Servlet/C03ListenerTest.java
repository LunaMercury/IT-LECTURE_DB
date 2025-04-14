package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/session/remove", "/session/attr/add","/session/attr/replace","/session/attr/remove" })
public class C03ListenerTest extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("[SERVLET] C03Listener Test");
		String uri = req.getRequestURI();
		// 아래 코드는 session을 가져오고, 없으면 새로운 세션을 생성한다. 하지만,
		// getSession(false) 라 작성하면 세션이 만료되더라도 새로운 세션을 생성하지 않는다.
		// 여기서는 없으면 새로 생성해도 괜찮다.
		HttpSession session = req.getSession();

		if (uri.contains("/session/remove")) {
			System.out.println("세션 제거");
			// 현재 세션을 완전히 무효화
			// 세션에 저장된 모든 속성을 제거
			// 세션 ID를 무효화하고 새로은 세션을 시작
			// 로그아웃과 같이 사용자 세션을 완전히 종료해야 할 때 사용			
			session.invalidate();
			
		} else if (uri.contains("/session/attr/add")) {			
			System.out.println("세션 추가");			
			session.setAttribute("S_KEY", "S_VAL");
		}else if (uri.contains("/session/attr/replace")) {
			System.out.println("세션 변경");
			session.setAttribute("S_KEY", "S_VAL_2");
		}else if (uri.contains("/session/attr/remove")) {
			System.out.println("세션 제거(해당 세션만)");
			// 현재 세션에서 지정된 이름의 속성만 제거
			// 세션 ID나 다른 속성은 그대로 유지			
			session.removeAttribute("S_KEY");
			
		}
	}
}
