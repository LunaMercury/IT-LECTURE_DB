package Domain.Dao;

import java.sql.SQLException;
import java.util.List;

import Domain.Dto.BookDto;
import Domain.Dto.Criteria;
import Domain.Dto.UserDto;

public interface BookDao {

	int insert(BookDto bookDto) throws Exception;

	int update(BookDto bookDto) throws SQLException;

	int delete(BookDto bookDto) throws SQLException;
	
	//단건조회
	UserDto select(String bookcode) throws SQLException;
	
	//다건조회
	List<BookDto> selectAll() throws SQLException;

	List<BookDto> selectAll(Criteria criteria);

	List<BookDto> selectAll(int offset, int amount) throws SQLException;

}