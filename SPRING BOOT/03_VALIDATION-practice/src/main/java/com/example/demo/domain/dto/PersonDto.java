package com.example.demo.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

//@Getter
//@Setter
//@ToString
@Data //getter,setter,tostring 모두 한번에 생성
@AllArgsConstructor    // 전체 생성자
@NoArgsConstructor // 디폴트 생성자
@Component // 객체를 bean 으로 인식하여 사용(rootcontext에서 참조할 수 있게, 일종의 싱글톤 역할)
@Builder // 객체를 생성하는 생성패턴
public class PersonDto {
	private String username;
	private int age;
	private String addr;
}