package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/index.do", "/main.do"})
public class Home extends HttpServlet {
	// GET - /main.do - /WEB-INF/main.jsp 연결
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uri = req.getRequestURI();
		System.out.println(uri);
		if (uri.contains("/index.do")) {
			System.out.println("GET /index.do ...");
			req.getRequestDispatcher("/WEB-INF/index.jsp").forward(req, resp);
			return;
		}
		else {
			System.out.println("GET /main.do ...");
			req.getRequestDispatcher("/WEB-INF/main.jsp").forward(req, resp);
			return;
		}		
	}	
}
