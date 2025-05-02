package com.example.app.domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.app.domain.dto.MemoDto;

@Repository
public class MemoDaoImpl {

	@Autowired
	private SqlSession sqlSession;

	// MemoMapper 끝에 점 찍어줘야 한다.
	private static String namespace = "com.example.app.domain.mapper.MemoMapper.";

	public int insert(MemoDto memoDto) throws SQLException {
		sqlSession.insert(namespace + "insert", memoDto); // namespace 안의 insert 메서드를 사용하고, memoDto 로 리턴한다.
		System.out.println("MemoDaoImpl's insert invoke..." + memoDto);
		return memoDto.getId();
	}

//	@Autowired
//	private DataSource dataSource1;
//	
//	public int insert(MemoDto memoDto) throws SQLException{
//		Connection conn = dataSource1.getConnection();
//		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo value(?,?,?,?)");
//		pstmt.setInt(1, memoDto.getId());
//		pstmt.setString(2, memoDto.getText());
//		pstmt.setString(3, memoDto.getWriter());
//		pstmt.setTimestamp(4, Timestamp.valueOf( memoDto.getCreateAt()));
//		int result = pstmt.executeUpdate();
//		return result;
//	}

}
