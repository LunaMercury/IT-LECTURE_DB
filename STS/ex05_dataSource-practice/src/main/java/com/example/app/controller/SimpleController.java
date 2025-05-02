package com.example.app.controller;

import java.io.FileNotFoundException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/test")
public class SimpleController {
	
	@GetMapping("/ex")
	public void ex1_1() throws FileNotFoundException {
		log.info("GET /except/ex");
		throw new FileNotFoundException("파일을 찾을 수 없습니다..");
	}
	
	@RequestMapping("/test/test1")
	public void test1() {
		log.info("Get /test/test1...");
	}
	
}
