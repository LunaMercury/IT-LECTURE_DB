package com.club_matching.app.domain.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.club_matching.app.domain.dto.UserDto;

@Repository
public class UserDaoImpl {
	@Autowired
	private SqlSession sqlSession;
	
	private static String namespace = "com.club_matching.app.domain.mapper.UserMapper.";
	
	public String insert(UserDto userDto) {
		System.out.println("UserDaoImpl insert invoke start");
		sqlSession.insert(namespace + "insert", userDto);
		return userDto.getUsername();
	}

}
