package Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet("/TEST_01")
public class C01Servlet_Test extends HttpServlet {

	// 처음 만들어질 때 실행
	@Override
	public void init() throws ServletException { 
		System.out.println("INIT() INVOKE"); 
	}	
	
	// 페이지 새로고침 할 때마다 실행
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		System.out.println("SERVICE() invoke");
	}

	// 코드 등 수정 내용 등을 반영
	@Override
	public void destroy() {
		System.out.println("DESTROY() invoke");
	}	
}