package com.example.app.controller;

import java.beans.PropertyEditorSupport;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.domain.dto.MemoDto;
import com.example.app.domain.service.MemoServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/memo")
public class MemoController {
	
	@Autowired
	private MemoServiceImpl memoServiceImpl;
	
	@GetMapping("/ex")
	public void ex1_1() throws FileNotFoundException {
		log.info("GET /except/ex");
		throw new FileNotFoundException("파일을 찾을 수 없습니다..");
	}

	// 이 안에 있는 것을 바인딩(변환) 할 것
//	@InitBinder
//	public void dataBinder(WebDataBinder webDataBinder) {
//		log.info("MemoController's dataBinder..." + webDataBinder);
//		webDataBinder.registerCustomEditor(LocalDate.class, "dateTest", new DateTestEditor());
//	}

	@GetMapping("/add")
	public void add_get() {
		log.info("GET /memo/add...");
	}

	// Memo dto 유효성 검사 통과를 못하면 bindingResult에 저장된다.
	// 결과를 model 에 저장해서 다른 곳으로 보낼 수 있다.
	// 파라미터 받기는 (@Valid MemoDto dto)  여기에서 끝난다
	@PostMapping("/add")
	public void add_post(@Valid MemoDto dto, BindingResult bindingResult, Model model) throws Exception {
		log.info("POST /memo/add..." + dto);
		
		// 유효성 검사
		if (bindingResult.hasErrors()) {
//			log.info("유효성 에러 발생 : " + bindingResult.getFieldError("id").getDefaultMessage());
			for (FieldError error : bindingResult.getFieldErrors()) {
				log.info("ERROR FIELD : " + error.getField() + "error message" + error.getDefaultMessage());
				model.addAttribute(error.getField(), error.getDefaultMessage());
			}
			
			return; // 이거 없으면 잘못된 값을 써도 DB에 들어간다.
		}
		
		// 서비스
		boolean isAdded = memoServiceImpl.registrationMemo(dto);
	}

	// static private
	public class DateTestEditor extends PropertyEditorSupport {

		@Override
		public void setAsText(String dateTest) throws IllegalArgumentException {
			log.info("DateTestEditor's setAsText invoke.." + dateTest);
			LocalDate date = null;
			if (dateTest.isEmpty()) {
				date = LocalDate.now();
			} else {
				// yyyy#MM#dd -> yyyy-MM-dd
				dateTest = dateTest.replaceAll("#", "-");
				date = LocalDate.parse(dateTest,DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
			
			setValue(date);			
		}		
	}
}
