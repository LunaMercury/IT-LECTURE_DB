// 아직 미작성

package Controller.book;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Service.BookServiceImpl;

public class BookReadController implements SubController {
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private BookServiceImpl bookService;

	public BookReadController() throws Exception {
		this.bookService = BookServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		System.out.println("[SC] BookReadController execute..");

		try {
			// 파라미터 받기
			String bookCode = req.getParameter("bookCode");
			String pageno = req.getParameter("pageno");			
			
			// 유효성 체크
			if(!isValid(bookCode)) {
				resp.sendRedirect(req.getContextPath()+"/book/list");
			}
			
			//서비스
			Map<String,Object> serviceResponse = bookService.getBook(bookCode);
			
			Boolean status = (Boolean)serviceResponse.get("status");
			if(status)
				req.setAttribute("bookDto", serviceResponse.get("bookDto"));
			
			req.setAttribute("pageno", pageno);
			//뷰
			req.getRequestDispatcher("/WEB-INF/view/book/read.jsp").forward(req, resp);
			
		} catch (Exception e) {
			exceptionHandler(e);
			try {
				req.getRequestDispatcher("/WEB-INF/view/book/error.jsp").forward(req, resp);
			}catch(Exception e2) {}
		}
	}

	// 유효성 검사 함수
	private boolean isValid(String bookCode) {
		if (bookCode.isEmpty()) {
			System.out.println("[BC] BookCode 유효성 오류");
			req.setAttribute("bookCode", "BookCode 유효성 오류");
		}
		return true;
	}

	// 예외처리함수
	public void exceptionHandler(Exception e) {
		req.setAttribute("status", false);
		req.setAttribute("message", e.getMessage());
		req.setAttribute("exception", e);
	}

}
