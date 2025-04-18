package Controller.book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.SubController;
import Domain.Dto.BookDto;
import Domain.Service.BookServiceImpl;

public class BookUpdateController implements SubController {
	HttpServletRequest req;
	HttpServletResponse resp;
	private BookServiceImpl bookService;

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		System.out.println("[SC] BookUpdateController execute..");

		String uri = req.getMethod();

		try {
			if (uri.equals("GET")) {
				req.getRequestDispatcher("/WEB-INF/view/book/read.jsp").forward(req, resp);
			}

			// 파라미터
			String bookCode = req.getParameter("bookCode");
			String bookName = req.getParameter("bookName");
			String publisher = req.getParameter("publisher");
			String isbn = req.getParameter("isbn");
			BookDto bookDto = new BookDto(bookCode, bookName, publisher, isbn);

//			String pageno = req.getAttribute("pageDto");

			// 유효성 검사
			if (!isValid(bookDto)) {
				resp.sendRedirect(req.getContextPath() + "/book/read?bookCode=" + bookCode);
			}

			// 서비스
			boolean isUpdate = bookService.modifyBook(bookDto);

			if (!isUpdate) {
				resp.sendRedirect(req.getContextPath() + "/book/read?bookCode=" + bookCode);
			}

			// 뷰
			resp.sendRedirect(req.getContextPath() + "/book/list?pageno=" + pageno);

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private boolean isValid(BookDto bookDto) {

		return true;
	}

}
