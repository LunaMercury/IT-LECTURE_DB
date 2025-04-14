package filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import type.Role;

// @WebFilter("/login.do")
public class LoginRedirectFilter implements Filter {

	// URL : login.do
	// 로그인 성공 시 redirect 경로를 role 별로 하기
	// ROLE_ADMIN -> /admin_main 으로 리다이렉트
	// ROLE_MANAGER -> ...

//	private static Map<Role, String> REDIRECT_MAP = new HashMap<>();
//
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//		REDIRECT_MAP.put(Role.ROLE_ADMIN, "/admin_main");
//		REDIRECT_MAP.put(Role.ROLE_MANAGER, "/manager_main");
//		REDIRECT_MAP.put(Role.ROLE_USER, "/user_main");
//	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("[FILTER] LoginRedirectFilter Filter Start");
		chain.doFilter(req, resp);

		// response 후
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		String method = request.getMethod();
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");

		if (method.contains("POST") && (role != null)) {
			System.out.println("로그인 된 상태 확인" + role);

			switch (role) {
			case "ROLE_USER": {
				response.sendRedirect(request.getContextPath() + "/user_main");
				return;
			}
			case "ROLE_MANAGER": {
				response.sendRedirect(request.getContextPath() + "/manager_main");
				return;
			}
			case "ROLE_ADMIN": {
				response.sendRedirect(request.getContextPath() + "/admin_main");
				return;
			}
			default:
				response.sendRedirect(request.getContextPath() + "/main.do");
				return;
			}
		}
		
		System.out.println("[FILTER] LoginRedirectFilter Filter End");
//		if (role!=null) {
//			String redirectUrl = REDIRECT_MAP.get(role);
//			if (redirectUrl != null) {
//				response.sendRedirect(request.getContextPath() + redirectUrl);
//			} else {
//				response.sendRedirect(request.getContextPath() + "/login.do?msg='invalid role'");
//			}			
//		} else {
//			response.sendRedirect(request.getContextPath() + "/login.do?msg='not authentificated'");
//		}

	}
}