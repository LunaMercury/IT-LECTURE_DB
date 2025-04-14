package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements SubController {
	HttpServletRequest req;
	HttpServletResponse resp;

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			this.req = req;
			this.resp = resp;
			req.getRequestDispatcher("/WEB-INF/view/index.jsp").forward(req, resp);
		} catch (ServletException | IOException e) {
			exceptionHandler(e);
			e.printStackTrace();
		}
	}

	public void exceptionHandler(Exception e) {
		req.setAttribute("status", false);
		req.setAttribute("message", e.getMessage());
		req.setAttribute("exception", e);
	}
}
