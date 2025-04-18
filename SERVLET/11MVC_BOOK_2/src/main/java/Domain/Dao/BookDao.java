package Domain.Dao;

import java.sql.SQLException;
import java.util.List;

import Domain.Dto.BookDto;
import Domain.Dto.Criteria;
import Domain.Dto.UserDto;

public interface BookDao {

	int insert(BookDto bookDto) throws Exception;

	int update(BookDto bookDto) throws SQLException;

	int delete(String bookcode) throws SQLException;
	
	//단건조회

	public BookDto select(String bookcode) throws Exception;
	//다건조회
	public List<BookDto> selectAll() throws Exception;

	public List<BookDto> selectAll(Criteria criteria) throws Exception;	
	
	public long count(String type, String keyword) throws Exception;
}