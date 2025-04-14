package Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.user.UserCreateController;

@WebServlet("/") // /는 프로젝트 내의 모든 경로
public class FrontController extends HttpServlet {

	private HttpServletRequest req;
	private HttpServletResponse resp;
	private Map<String, SubController> map = new HashMap(); //subcontroller 받기 위해

	@Override
	public void init(ServletConfig config) throws ServletException {

		try {
			// 기본
			map.put("/", new HomeController());
			map.put("/index.do", new HomeController());
			
			// 인증 - 회원CRUD, 로그인, 로그아웃
			map.put("/user/create", new UserCreateController());
			
			// 도서
			

		} catch (Exception e) {
			exceptionHandler(e);			
			throw new ServletException("서브컨트롤러 등록오류");
		}

		// 도서 - 도서 CRUD
	}



	// execute 함수 대체
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		try {
			System.out.println("[FC] service...");
			String endPoint = req.getServletPath();
			System.out.println("[FC] endpoint : " + endPoint);
			SubController controller = map.get(endPoint);
			controller.execute(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionHandler(e);
			req.getRequestDispatcher("/WEB-INF/view/globalError.jsp").forward(req, resp);
		}

	}

	// 예외처리함수
	public void exceptionHandler(Exception e) {
		req.setAttribute("status", false);
		req.setAttribute("message", e.getMessage());
		req.setAttribute("exception", e);
	}

}
