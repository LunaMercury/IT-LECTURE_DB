// book에 관련된 수정 삭제 등의 처리를 하는 곳. 
// DB 관련이면 DaoImpl을 불러와 메서드를 사용한다.

package Domain.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import Domain.Dao.BookDao;
import Domain.Dao.BookDaoImpl;
import Domain.Dto.BookDto;
import Domain.Dto.Criteria;

public class BookServiceImpl {

	//
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

	// TX 처리 + 비즈니스 유효성검사
	public boolean bookRegistration(BookDto bookDto) throws Exception {
		int result = bookDao.insert(bookDto);
		return result > 0;
	}

	public Map<String, Object> getAllBooks() throws Exception {
		Map<String, Object> response = new LinkedHashMap<>();
		List<BookDto> list = bookDao.selectAll();
		if (list.size()!=0) {
			response.put("status", true);
			response.put("list", list);
		} else {
			response.put("status", false);
		}
		return response;
	}

	public Map<String, Object> getAllBooks(Criteria criteria) throws Exception {
		Map<String, Object> response = new LinkedHashMap<>();
		
		int offset = (criteria.getPageno() -1)*criteria.getAmount();
		
		// 페이지별 건수
		List<BookDto> list = bookDao.selectAll(offset, criteria.getAmount());
		if (list.size()!=0) {
			response.put("status", true);
			response.put("list", list);
		} else {
			response.put("status", false);
		}
		return response;
	}
}
