package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/test")
public class SimpleController {
	
	@RequestMapping( value="/test1", method=RequestMethod.GET)
	public void test1() {
		log.info("GET /test/test1....");
		// 파라미터
		// 유효성
		// 서비스
		// 뷰
		// -> 메서드명과 파일명이 같아야 한다
	}
	@RequestMapping( value="/test2", method=RequestMethod.GET)	
	public String test2() {
		log.info("GET /test/test2....");
		// 메서드명과 파일명이 같지 않으면 메서드 리턴값과 형식을 바꿔야 한다.(String, return ~~~~)
		return "test/abcd";
	}
	
	@RequestMapping( value="/test3", method= {RequestMethod.GET, RequestMethod.POST})
	public void test3() {
		log.info("GET /test/test3....");
	}
}