package Controller.book;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Controller.SubController;
import Domain.Dto.BookDto;
import Domain.Dto.Criteria;
import Domain.Dto.PageDto;
import Domain.Service.BookServiceImpl;

public class BookListController implements SubController {
	private HttpServletRequest req;
	private HttpServletResponse resp;
	private BookServiceImpl bookService;

	public BookListController() throws Exception {
		this.bookService = BookServiceImpl.getInstance();
	}

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		this.req = req;
		this.resp = resp;
		System.out.println("[SC] BookListController execute..");

		try {
			// 파라미터
			String pageno = req.getParameter("pageno"); // 사용자가 요청한 현재 페이지 번호
//			String amount = req.getParameter("amount"); // 페이지 당 항목 수
			String type = req.getParameter("type"); // 사용자가 어떤 기준으로 도서를 검색하고 싶어 하는지를 나타내는 파라미터. 도서 제목으로 검색할지, 출판사로 검색할지
			String keyword = req.getParameter("keyword"); // 키워드 검색 시 사용

			HttpSession session = req.getSession();
			System.out.println("[BookListCtrl] session" + session.getAttribute("searchKeyword"));
			System.out.println("[BookListCtrl] session" + session.getAttribute("searchType"));

			if (session.getAttribute("searchKeyword") != null && keyword == null) {
				keyword = (String) session.getAttribute("searchKeyword");
				type = (String) session.getAttribute("searchType");
			}

			// 세션에 담기
			session.setAttribute("pageno", pageno);
			session.setAttribute("searchType", type);
			session.setAttribute("searchKeyword", keyword);

			Criteria criteria = null;
			if (pageno == null) {
				criteria = new Criteria();

			} else {
				criteria = new Criteria(pageno, 10); // 원래 10대신 amount를 사용해야 하는데 그럼 복잡해진다.
			}

			// 입력값
			// nav에 값 등을 입력하면 여기서 받아서 req에 넣는다.
			if (type != null && !type.isEmpty()) {
				criteria.setType(type);
			} else {
				criteria.setType("bookCode");
			}
			if (keyword != null && !keyword.trim().isEmpty()) {
				criteria.setKeyword(keyword);
			}

			// 서비스
			Map<String, Object> serviceResponse = bookService.getAllBooks(criteria);
			Boolean status = (Boolean) serviceResponse.get("status");
			PageDto pageDto = (PageDto) serviceResponse.get("pageDto");

			List<BookDto> list = (List<BookDto>) serviceResponse.get("list");
			req.setAttribute("list", list);
			req.setAttribute("pageDto", pageDto);

			req.getRequestDispatcher("/WEB-INF/view/book/list.jsp").forward(req, resp);

		} catch (Exception e) {
			exceptionHandler(e);
			try {
				req.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(req, resp);
			} catch (Exception e2) {
			}
		}

	}

	// 예외처리함수
	public void exceptionHandler(Exception e) {
		req.setAttribute("status", false);
		req.setAttribute("message", e.getMessage());
		req.setAttribute("exception", e);
	}

}
