package com.example.app.domain.service;

import java.sql.SQLException;

import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.domain.dao.MemoDaoImpl;
import com.example.app.domain.dto.MemoDto;
import com.example.app.domain.mapper.MemoMapper;

@Service
public class MemoServiceImpl {
	// Mapper.java 를 이용할 때
//	@Autowired	
//	private MemoDaoImpl memoDaoImpl;	
//	public boolean registrationMemo(MemoDto memoDto) throws SQLException {
//		int result = memoDaoImpl.insert(memoDto);
//		return result>0;
//	}
	
	// Mapper.xml 을 이용할 때
	@Autowired
	private MemoMapper memoMapper;
	public boolean registrationMemo(MemoDto memoDto) throws SQLException {
		int result = memoMapper.insert(memoDto);		
		return result>0;		
	}	
}