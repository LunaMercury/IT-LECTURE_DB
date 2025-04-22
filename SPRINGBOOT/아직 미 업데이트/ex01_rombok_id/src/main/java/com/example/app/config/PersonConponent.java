// 환경 설정 파일
// 버전관리

package com.example.app.config;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data	//toString, getter,setter
public class PersonConponent {
	private String username;
	private int age;
	private String addr;
	
	PersonConponent(){
		this.username = "티모";
		this.age=33;
		this.addr="인천";
	}
}
