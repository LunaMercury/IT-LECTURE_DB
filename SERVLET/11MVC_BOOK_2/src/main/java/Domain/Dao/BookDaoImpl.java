package Domain.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import Domain.Dao.ConnectionPool.ConnectionItem;
import Domain.Dao.ConnectionPool.ConnectionPool;
import Domain.Dto.BookDto;
import Domain.Dto.Criteria;
import Domain.Dto.UserDto;

public class BookDaoImpl implements BookDao {

	// DB 연결
	private PreparedStatement pstmt;
	private ResultSet rs;

	private ConnectionPool connectionPool;
	private ConnectionItem connectionItem;

	private static BookDao instance;

	private BookDaoImpl() throws SQLException {
		connectionPool = ConnectionPool.getInstance();
		System.out.println("UserDaoImpl DB Connection Success");
	}

	public static BookDao getInstance() throws SQLException {
		if (instance == null) {
			instance = new BookDaoImpl();
		}
		return instance;
	}

	@Override
	public int insert(BookDto bookDto) throws Exception {
		try {
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();

			pstmt = conn.prepareStatement("insert into tbl_book values(?,?,?,?)");
			pstmt.setString(1, bookDto.getBookCode());
			pstmt.setString(2, bookDto.getBookName());
			pstmt.setString(3, bookDto.getPublisher());
			pstmt.setString(4, bookDto.getIsbn());

			// connection release
			connectionPool.releaseConnection(connectionItem);

			return pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO's INSERT SQL EXCEPTION!!");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
			}
		}

	}

	@Override
	public List<BookDto> selectAll() throws Exception {
		List<BookDto> list = new LinkedList();
		BookDto dto = null;

		try {
			// connection get
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();

			pstmt = conn.prepareStatement("select * from tbl_book");

			rs = pstmt.executeQuery();

			if (rs != null) {

				while (rs.next()) {
					dto = new BookDto();
					dto.setBookCode(rs.getString(1));
					dto.setBookName(rs.getString(2));
					dto.setPublisher(rs.getString(3));
					dto.setIsbn(rs.getString(4));

					list.add(dto);
				}
			}

			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO's SELECT SQL EXCEPTION!!");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
			}
			// connection release
			connectionPool.releaseConnection(connectionItem);
		}
	}

	// 몇 번째 목록부터 몇개 볼건지(블록아님. 목록임)
	public List<BookDto> selectAll(Criteria criteria) throws Exception {
		List<BookDto> list = new LinkedList();
		BookDto dto = null;
		try {
			// connection get
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();

			int offset = (criteria.getPageno() - 1) * criteria.getAmount();

			// 아래 sql 코드로 인해, 4페이지를 누르면 31번 글부터 40번 글까지 10개가 나타난다.
			String sql = "select * from tbl_book";
			int parameterIndex = 1;

			if (criteria.getType() != null && !criteria.getType().isEmpty() && criteria.getKeyword() != null
					&& !criteria.getKeyword().isEmpty()) {
				sql += " where " + criteria.getType() + " like ?";
			}
			sql += " order by bookCode desc limit ?,?";

			pstmt = conn.prepareStatement(sql);

			if (criteria.getType() != null && !criteria.getType().isEmpty() && criteria.getKeyword() != null
					&& !criteria.getKeyword().isEmpty()) {
				pstmt.setString(parameterIndex++, "%" + criteria.getKeyword() + "%");
			}
			pstmt.setInt(parameterIndex++, offset);
			pstmt.setInt(parameterIndex++, criteria.getAmount());

			rs = pstmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					dto = new BookDto();
					dto.setBookCode(rs.getString(1));
					dto.setBookName(rs.getString(2));
					dto.setPublisher(rs.getString(3));
					dto.setIsbn(rs.getString(4));
					list.add(dto);
				}
			}
			return list;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO's SELECT SQL EXCEPTION!!");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
			}
			// connection release
			connectionPool.releaseConnection(connectionItem);
		}
	}

	// 총 게시물 갯수(페이지 갯수가 아님. 글의 갯수)
	@Override
	public long count(String type, String keyword) throws Exception {
		long count = 0;
		try {
			// connection get
			connectionItem = connectionPool.getConnection();
			Connection conn = connectionItem.getConn();

			String sql = "select count(*) from tbl_book";
			if (type != null && !type.isEmpty() && keyword != null && !keyword.isEmpty()) {
				sql += " where " + type + " like ?";
			}
			///////////////////// 이 근처 부분 다시 원래대로 되돌리고 Controller 의 Criteria 를 if 문 넣어서 초기값 잡기
			pstmt = conn.prepareStatement(sql);
			

			rs = pstmt.executeQuery();

			if (rs != null && rs.next())
				count = rs.getLong(1);

			return count;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("BOOKDAO's SELECT SQL EXCEPTION!!");
		} finally {
			try {
				pstmt.close();
			} catch (Exception e2) {
			}
			// connection release
			connectionPool.releaseConnection(connectionItem);
		}
	}

	@Override
	public int update(BookDto bookDto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(BookDto bookDto) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserDto select(BookDto bookDto) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
