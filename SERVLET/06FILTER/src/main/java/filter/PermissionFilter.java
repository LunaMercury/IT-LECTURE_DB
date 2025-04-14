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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import type.Role;

public class PermissionFilter implements Filter {

	// URL : Permission Value
	private Map<String, Role> pageGradeMap = new HashMap<>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

		// 웹 애플리케이션의 컨텍스트 경로. 여기서는 http://localhost:8090/06FILTER 이다.
		String projectPath = filterConfig.getServletContext().getContextPath();
//		/admin_main		권한값(3) - ROLE_ADMIN
//		/manager_main	권한값(2) - ROLE_MANAGER
//		/user_main		권한값(1) - ROLE_USER

		pageGradeMap.put(projectPath + "/admin_main", Role.ROLE_ADMIN);
		pageGradeMap.put(projectPath + "/manager_main", Role.ROLE_MANAGER);
		pageGradeMap.put(projectPath + "/user_main", Role.ROLE_USER);

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("[FILTER] Permission Filter Start");

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession();
		String myRole = (String) session.getAttribute("role");

		if (myRole == null) {
			response.sendRedirect(request.getContextPath() + "/login.do?msg='not authenticated'");
			return;
		}

		//
		Role my = null;
		switch (myRole) {
		case "ROLE_USER": {
			my = Role.ROLE_USER;
			break;
		}
		case "ROLE_MANAGER": {
			my = Role.ROLE_MANAGER;
			break;
		}
		case "ROLE_ADMIN": {
			my = Role.ROLE_ADMIN;
			break;
		}
		default:
			my = Role.ROLE_ANNONYMOUS;
			break;
		}

		// Page Role Value 꺼내기
		String requestUri = request.getRequestURI();
		Role pageRole = pageGradeMap.get(requestUri);

		System.out.printf("URL : %s, MyRole : %d, PageRole : %d\n", requestUri, my.ordinal(), pageRole.ordinal());

		if (my.ordinal() < pageRole.ordinal()) {
			throw new ServletException("해당 권한으로는 접근 불가");
		}

		chain.doFilter(req, resp);
		response.sendRedirect(request.getContextPath() + "/LoginRedirect");

		System.out.println("[FILTER] Permission Filter End");

	}
}
