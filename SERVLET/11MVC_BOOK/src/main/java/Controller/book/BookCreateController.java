package Controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Dto.BookDto;
import Domain.Service.BookServiceImpl;

public class BookCreateController implements SubController {
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private BookServiceImpl bookService;

	public BookCreateController() throws Exception {
		bookService = BookServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		System.out.println("[SC] BookCreateController execute..");

		try {
			if (req.getMethod().equals("GET")) {
				req.getRequestDispatcher("/WEB-INF/view/book/create.jsp").forward(req, resp);
				return;
			}

			String bookCode = req.getParameter("bookcode");
			String bookname = req.getParameter("bookname");
			String publisher = req.getParameter("publisher");
			String isbn = (int) ((Math.random() * 1000) % 899 + 100) + "-" + (int) ((Math.random() * 1000) % 899 + 100);

			BookDto bookDto = new BookDto(bookCode, bookname, publisher, isbn);

			// 유효성
			boolean isOk = isValid(bookCode, bookname, publisher);
			if (!isOk) {
				req.getRequestDispatcher("/WEB-INF/view/book/create.jsp").forward(req, resp);
				return;
			}

			// 서비스
			boolean isadded = bookService.bookRegistration(bookDto);

			// 뷰
			if (isadded) {
				resp.sendRedirect(req.getContextPath() + "/book/list");
				return;
			} else {
				req.getRequestDispatcher("/WEB-INF/view/book/create.jsp").forward(req, resp);
				return;
			}

		} catch (Exception e) {
			exceptionHandler(e);
			try {
				req.getRequestDispatcher("/WEB-INF/view/user/error.jsp").forward(req, resp);
			} catch (Exception e2) {
			}
		}

	}

	// 유효성 검사
	private boolean isValid(String bookcode, String bookname, String publisher) {

		if (bookcode == null || bookcode.trim().isEmpty()) {
			System.out.println("[INVALID] 북코드 오류");
			req.setAttribute("bookcode_error", "북코드 에러");
			return false;
		}
		if (bookname == null || bookname.trim().isEmpty()) {
			System.out.println("[INVALID] 책이름 오류");
			req.setAttribute("bookname_error", "책이름 에러");
			return false;
		}
		if (publisher == null || publisher.trim().isEmpty()) {
			System.out.println("[INVALID] 작가 오류");
			req.setAttribute("publisher_error", "작가이름 에러");
			return false;
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
