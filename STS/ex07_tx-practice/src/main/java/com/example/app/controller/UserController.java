package com.example.app.controller;

import java.beans.PropertyEditorSupport;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.app.domain.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	@GetMapping("/ex")
	public void ex1_1() throws FileNotFoundException {
		log.info("GET /except/ex");
		throw new FileNotFoundException("파일을 찾을 수 없습니다..");
	}
	
	@InitBinder
	public void dataBinder(WebDataBinder webDataBinder) {
		log.info("UserControllerDateBinder" + webDataBinder);
		webDataBinder.registerCustomEditor(LocalDate.class, "birthday", new BirthdayEditor());
		webDataBinder.registerCustomEditor(String.class, "phone", new PhoneNumberEditor());
	}
	
	@GetMapping("/join")
	public void join() {
		log.info("GET /join...");
	}

	@PostMapping("/join")
	public void join_post(@Valid UserDto dto, BindingResult bindingResult, Model model) {
		log.info("POST /join..." + dto);
		if (bindingResult.hasErrors()) {
			for (FieldError error : bindingResult.getFieldErrors()) {
				log.info("ERROR field : " + error.getField() + "error message : " + error.getDefaultMessage());
				model.addAttribute(error.getField(), error.getDefaultMessage());
			}
		}
	}
	
	public class BirthdayEditor extends PropertyEditorSupport {

		@Override
		public void setAsText(String birthday) throws IllegalArgumentException {
			LocalDate date = null;
			if (birthday.isEmpty()) {
				date = LocalDate.now();
			} else {
				birthday = birthday.replaceAll("~", "-");
				date = LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
			
			setValue(date);	
		}		
	}
	public class PhoneNumberEditor extends PropertyEditorSupport{

		@Override
		public void setAsText(String phone) throws IllegalArgumentException {
			String phonenumber = null;
			if (phone.isEmpty()) {
				phonenumber = "00099999999";
			} else {				
				phone = phone.replaceAll("[-/\\\\s().]", "");
				phonenumber = phone;
			}
			setValue(phonenumber);
		}		
	}
}
