package com.club_matching.app.domain.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.club_matching.app.domain.dto.UserDto;

@Mapper
public interface UserMapper {
	public int insertXml(UserDto userDto);
}
