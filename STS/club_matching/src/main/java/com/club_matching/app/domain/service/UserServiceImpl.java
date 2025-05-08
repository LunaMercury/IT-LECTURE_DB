package com.club_matching.app.domain.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.club_matching.app.domain.dao.UserDaoImpl;
import com.club_matching.app.domain.dto.UserDto;
import com.club_matching.app.domain.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl {

	@Autowired
	private UserDaoImpl userDaoImpl;

	public boolean userJoin(UserDto userDto) throws SQLException {
		String result = userDaoImpl.insert(userDto);		
		return result != null;
	}
}
