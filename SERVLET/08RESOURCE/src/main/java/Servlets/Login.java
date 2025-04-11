package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Utils.MysqlDbUtils;
import Utils.UserDto;

@WebServlet("/login.do")
public class Login extends HttpServlet {

	private MysqlDbUtils dbUtils;

	@Override
	public void init() throws ServletException {
		try {
			dbUtils = MysqlDbUtils.getInstance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// GET - /login.do - /WEB-INF/user/login.jsp 연결
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET /login.do");
		req.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(req, resp);
	}

	// POST - /login.do - 회원가입처리(username,password 받아 DBUtils를 이용한 DB INSERT)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 파라미터 받기
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		// 유효성 - 생략
		// 작업
		boolean isAuth = false;
		
		try {
			UserDto dbUser = dbUtils.selectOne(username);
			if (dbUser != null && dbUser.getPassword().equals(password)) {
				// 세션 작업
				HttpSession session = req.getSession();
				session.setAttribute("username", username);
				session.setAttribute("role", dbUser.getRole());
				isAuth=true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 뷰
		if (isAuth) {
			resp.sendRedirect(req.getContextPath()+"/main.do");
			return;
		} else {
			req.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(req, resp);
			return;
		}

	}

	// 테이블 : tbl_user
	// 성공시 : /main.do 로 리다이렉트
	// 실패시 : /login.do로 포워딩

}
