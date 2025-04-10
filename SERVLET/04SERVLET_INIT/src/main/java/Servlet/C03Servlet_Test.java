package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/main.do") // 링크명
public class C03Servlet_Test extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("GET /main.do...");
		req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp); // 위 파일(또는 페이지)에 위와 같은 이름으로 정보 연결. 이경우에는 받는다
	}
	
}
