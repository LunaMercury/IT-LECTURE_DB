// 여기서 BookDaoimpl의 메서드를 사용해서 결과값을 받아 다시 BookListController로 보낸다.

package Domain.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Domain.Dao.BookDao;
import Domain.Dao.BookDaoImpl;
import Domain.Dto.BookDto;
import Domain.Dto.Criteria;
import Domain.Dto.PageDto;

public class BookServiceImpl {

	private BookDao bookDao;

	// 싱글톤 패턴
	private static BookServiceImpl instance;

	private BookServiceImpl() throws Exception {
		bookDao = BookDaoImpl.getInstance();
	}

	public static BookServiceImpl getInstance() throws Exception {
		if (instance == null)
			instance = new BookServiceImpl();
		return instance;
	}

	// TX 처리 + 비즈니스 유효성검사(도서추가 -)
	public boolean bookRegistration(BookDto bookDto) throws Exception {
		int result = bookDao.insert(bookDto);
		return result > 0;
	}

	// 조건 없이 모든 책 조회 (사용안함. 무조건 책 조회도 아래 criteria를 사용한 메서드를 사용)
	public Map<String, Object> getAllBooks() throws Exception {
		Map<String, Object> response = new LinkedHashMap();
		List<BookDto> list = bookDao.selectAll();
		if (list.size() > 0) {
			response.put("status", true);
			response.put("list", list);

		} else {
			response.put("status", false);
		}

		// list를 return 하지 않아도 되는 이유는 response 에 list 가 담겨져 있기 때문
		return response;
	}

	// 조건 설정하여 책 조회 (키워드로 조회한다던지 등등)
	public Map<String, Object> getAllBooks(Criteria criteria) throws Exception {
		Map<String, Object> response = new LinkedHashMap<>();

		// 페이지별 글
		List<BookDto> list = bookDao.selectAll(criteria);

		// PageDto, endPage 및 startPage 값을 계산하기 위해
		String type = criteria.getType();
		String keyword = criteria.getKeyword();
		System.out.println("BookService 타입, 키워드 : " + type + keyword);
		long totalCount = bookDao.count(type, keyword); // 총 목록갯수
		PageDto pageDto = new PageDto(totalCount, criteria);
		System.out.println("Service pageDto : " + pageDto);

		if (list.size() > 0) {
			response.put("status", true);

		} else {
			response.put("status", false);
		}
		response.put("list", list);
		response.put("pageDto", pageDto);

		return response;
	}

	public Map<String, Object> getBook(String bookCode) throws Exception {

		Map<String, Object> response = new LinkedHashMap();

		BookDto bookDto = bookDao.select(bookCode);

		if (bookDto == null)
			response.put("status", false);
		else {
			response.put("status", true);
			response.put("bookDto", bookDto);
		}
		return response;
	}

	public boolean modifyBook(BookDto bookDto) throws Exception {		
		
		int result = bookDao.update(bookDto);

		return result > 0;
	}

	public boolean removeBook(String bookCode) throws Exception {

		int result = bookDao.delete(bookCode);

		return result > 0;
	}

}
