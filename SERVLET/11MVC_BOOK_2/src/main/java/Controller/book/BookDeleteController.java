package Controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Dto.BookDto;
import Domain.Service.BookServiceImpl;

public class BookDeleteController implements SubController{
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private BookServiceImpl bookService;

	public BookDeleteController() throws Exception {
		this.bookService = BookServiceImpl.getInstance();
	}
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		System.out.println("[SC] BookDeleteController execute..");

		String uri = req.getMethod();

		try {
			// 파라미터
			String bookCode = req.getParameter("bookCode");
			String pageno = (String) req.getAttribute("pageDto")!=null?req.getParameter("pageno"):"1";

			// 유효성 검사
			if (!isValid(bookCode)) {
				resp.sendRedirect(req.getContextPath() + "/book/read?bookCode=" + bookCode);
			}

			// 서비스
			boolean isDelete = bookService.removeBook(bookCode);

			if (!isDelete) {
				resp.sendRedirect(req.getContextPath() + "/book/read?bookCode=" + bookCode);
			}

			// 뷰
			resp.sendRedirect(req.getContextPath() + "/book/list?pageno=" + pageno);

		} catch (Exception e) {
			exceptionHandler(e);
			try {
				req.getRequestDispatcher("/WEB-INF/view/book/error.jsp").forward(req, resp);
			} catch (Exception e2) {
			}
			
		}
	}

	private boolean isValid(String bookCode) {

		return true;
	}
	
	// 예외처리함수
	public void exceptionHandler(Exception e) {
		req.setAttribute("status", false);
		req.setAttribute("message", e.getMessage());
		req.setAttribute("exception", e);
	}


}
