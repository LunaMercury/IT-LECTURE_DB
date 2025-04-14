// 회원가입 컨트롤러

package Controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Dto.UserDto;
import Domain.Service.UserServiceImpl;

public class UserCreateController implements SubController {

	private HttpServletRequest req;
	private HttpServletResponse resp;
	private UserServiceImpl userService;

	public UserCreateController() throws Exception {
		userService = UserServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		System.out.println("[SC] UserCreateController execute");

		try {

			String uri = req.getMethod();
			if (uri.equals("GET")) {
				req.getRequestDispatcher("/WEB-INF/view/user/create.jsp").forward(req, resp);
				return;
			}

			// post 방식이면 아래의 작업을 행함
			// 파라미터 확인
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String role = "ROLE_USER";

			// 입력값
			UserDto userDto = new UserDto(username, password, role);
			boolean isOk = isValid(userDto);
			if (!isOk) {
				req.getRequestDispatcher("/WEB-INF/view/user/create.jsp").forward(req, resp);
				return;
			}

			// 서비스
			System.out.println("[UC] Service ...");
			boolean isJoin = userService.userJoin(userDto);

			// 뷰
			if (isJoin) {
				resp.sendRedirect(req.getContextPath() + "/index.do");
			} else {
				req.getRequestDispatcher("/WEB-INF/view/user/join.jsp").forward(req, resp);
			}

		} catch (Exception e) {
			exceptionHandler(e);
			try {
				req.getRequestDispatcher("/WEB-INF/view/user/error.jsp").forward(req, resp);
			} catch (Exception e2) {
			}
		}

	}

	private boolean isValid(UserDto userDto) {
		if (userDto.getUsername() == null || userDto.getUsername().length() <= 4) {
			req.setAttribute("username_error", "username의 길이는 최소 5자이상이어야합니다");
			System.out.println("[INVALID] username의 길이는 최소 5자이상이어야합니다");
			return false;
		}
		if (userDto.getUsername().matches("^[0-9].*")) {
			System.out.println("[INVALID] username의 첫문자로 숫자가 들어올수 없습니다");
			req.setAttribute("username_error", "username의 userid의 첫문자로 숫자가 들어올수 없습니다");
			return false;
		}
		return true;
	}

	public void exceptionHandler(Exception e) {
		req.setAttribute("status", false);
		req.setAttribute("message", e.getMessage());
		req.setAttribute("exception", e);
	}

}
