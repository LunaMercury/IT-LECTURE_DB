package Filter;

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

import role.Role;

@WebFilter({"/","/user/*"})
public class PermissionFilter implements Filter{
	
	private Map<String,Role> pageGradeMap = new HashMap();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println(filterConfig.getServletContext().getContextPath());
		pageGradeMap.put("/", Role.ROLE_ANONYMOUS);
		pageGradeMap.put("/index.do", Role.ROLE_ANONYMOUS);
		pageGradeMap.put("/user/create", Role.ROLE_ANONYMOUS);
		pageGradeMap.put("/user/login", Role.ROLE_ANONYMOUS);
		pageGradeMap.put("/user/admin", Role.ROLE_ADMIN);
		pageGradeMap.put("/user/manager",Role.ROLE_MANAGER);
		pageGradeMap.put("/user/user",Role.ROLE_USER);
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		System.out.println("[FILTER] Perm Filter start...");
		
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = request.getSession();
		
		String myRole = (String)session.getAttribute("role");
		if (myRole == null) {
			session.setAttribute("role", "ROLE_ANONYMOUS");
			response.sendRedirect(request.getContextPath()+"/user/login?message=not authenticated...");
			return ;
		}
		
		String reqUri = request.getServletPath();
		Role pageRole = pageGradeMap.get(reqUri);
		
		Role my = null;
		switch (myRole) {
		case "ROLE_USER":
			my = Role.ROLE_USER;
			break;
		case "ROLE_MANAGER":
			my = Role.ROLE_MANAGER;
			break;
		case "ROLE_ADMIN":
			my = Role.ROLE_ADMIN;
			break;
		default:
			my = Role.ROLE_ANONYMOUS;
		}
		System.out.printf("URL : %s\nMYRole : %d\nPageRole : %d\n",reqUri,my.ordinal(),pageRole.ordinal());
		
		if(pageRole.ordinal() > my.ordinal()) {
			throw new ServletException("권한이 부족합니다.");
		}
		
		chain.doFilter(req, resp);
		System.out.println("[FILTER] Perm Filter end...");
		
	}

}

