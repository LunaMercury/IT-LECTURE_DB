package com.example.app.domain.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
//@Data //getter,setter,tostring 모두 한번에 생성
@AllArgsConstructor	// 전체 생성자
@NoArgsConstructor // 디폴트 생성자
@Component // 객체를 bean 으로 인식하여 사용(rootcontext에서 참조할 수 있게, 일종의 싱글톤 역할)
@Builder //Bean 등록 등에 사용할 수 있도록 설정
public class PersonDto {
	private String username;
	private int age;
	private String addr;
}
