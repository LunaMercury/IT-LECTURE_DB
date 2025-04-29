package com.example.app.config;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class PersonComponent {
	private String username;
	private int age;
	private String addr;

	// 따로 이름을 정해주지 않았으므로 bean 참조 시 PersonComponent 로 찾을 수 있다. 
	PersonComponent() {
		this.username = "티모";
		this.age = 33;
		this.addr = "인천";
	}
}
