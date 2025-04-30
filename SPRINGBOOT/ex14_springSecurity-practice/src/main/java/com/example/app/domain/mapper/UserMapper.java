package com.example.app.domain.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.app.domain.dto.UserDto;

@Mapper
public interface UserMapper {
	
	@Insert(value = "insert into tbl_user value(#{username}, #{password}, #{role})")
	public int insert(UserDto userDto);
	
//	@Update(value = "update tbl_user set password=#{password} where userid=#{userid}")
//	public int update(UserDto userDto);
//	
//	@Delete(value = "delete from tbl_user where userid=#{userid}")
//	public int delete(String userid);
	
	@Select(value = "select * from tbl_user where username=#{username}")
	public UserDto selectAt(String username);
	
//	@Select(value = "select * from tbl_user")
//	public List<UserDto> selectAll();
	
//	//XML
//	public int userInsertXml(UserDto userDto);
//	public int userUpdateXml(UserDto userDto);
//	public int userDeleteXml(String userid);
//	public UserDto userSelectAtXml(String userid);
//	public List<UserDto> userSelectAllXml();
//	public List<Map<String, Object>> userSelectAllResultXml();
}
