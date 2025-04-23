package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/test")
public class SimpleController {
	
	@RequestMapping("/test/test1")
	public void test1() {
		log.info("Get /test/test1...");
	}
	
}
