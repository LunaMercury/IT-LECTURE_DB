package Controller.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.SubController;
import Domain.Dto.UserDto;
import Domain.Service.UserServiceImpl;

public class UserLogoutController implements SubController {

	private HttpServletRequest req;
	private HttpServletResponse resp;
	private UserServiceImpl userService;

	// FrontController -> UserLogoutController 등록 (URI : /user/logout)

	// UserLogoutController
	// - 1 파라미터받기 생략
	// - 2 session 안 속성 정보(isAuth , role , username) 꺼내오고 유효성 체크
	// - isAuth == false 라면 포워딩 /WEB-INF/login.jsp session에 message 속성 추가 "로그인상태가
	// 아닙니다"
	// UserService
	// - logout함수 내 처리
	// - session invalid 처리 하기
	// - Map<String,Object> 로 return "isLogout",true , "message","로그아웃성공"

	// UserLogoutController
	// - isLogout 정보를 확인하여 로그아웃 성공/실패 둘다 /login.do
	// - message는 session 에 저장

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;

		HttpSession session = req.getSession();

		boolean isAuth = session.getAttribute("isAuth") != null ? (boolean) session.getAttribute("isAuth") : false;
		System.out.println(isAuth);

		try {
			if (!isValid(session)) {
				req.setAttribute("message", "로그인 되어있지 않은 사용자입니다.");
				req.getRequestDispatcher("/WEB-INF/view/user/login.jsp").forward(req, resp);
				return;
			}
			Map<String, Object> rs = UserServiceImpl.getInstance().logout(session);
			req.setAttribute("message", rs.get("message"));
			req.getRequestDispatcher("/WEB-INF/view/user/login.jsp").forward(req, resp);

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private boolean isValid(HttpSession session) {
		if (session.getAttribute("username") == null || session.getAttribute("isAuth") == null) {

			return false;
		}
		// NULL 체크 / 데이터(자료)수준에서의 의미있는데이터가 포함되어져있는지 여부
		// userid 은 첫문자가 숫자인지 여부 - /or 길이가 1글자인지 등등..
		// username 은 첫문자가 숫자인지 여부 -
		// password 복잡도체크는 Business Layer 체크(Policy 에 의한 처리)

		return true;
	}

	// 예외처리함수
	public void exceptionHandler(Exception e) {
		req.setAttribute("status", false);
		req.setAttribute("message", e.getMessage());
		req.setAttribute("exception", e);
	}
}
