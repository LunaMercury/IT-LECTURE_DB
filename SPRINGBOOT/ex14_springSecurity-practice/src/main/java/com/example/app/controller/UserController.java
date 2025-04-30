package com.example.app.controller;

import java.beans.PropertyEditorSupport;
import java.io.FileNotFoundException;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.app.config.auth.PrincipalDetails;
import com.example.app.domain.dto.UserDto;
import com.example.app.domain.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	
	@Autowired
	private UserServiceImpl userService;
	
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
	
	@GetMapping("/login")
	public void login() {
		log.info("GET /login..");
	}
	
	@PostMapping("/login")
	public String login_post() {
		log.info("POST /login..");
		return "/manager";
	}
	
//	@GetMapping("/user")	
//	public void user(Authentication authentication) {
//		log.info("GET /user.." + authentication);
//		log.info("name.." + authentication.getName());
//		log.info("principal.." + authentication.getPrincipal());
//		log.info("authorities.." + authentication.getAuthorities());
//		log.info("details.." + authentication.getDetails());
//		log.info("credential.." + authentication.getCredentials());
//	}
	
//	@GetMapping("/user")	
//	public void user(@AuthenticationPrincipal Principal principal) {
//		log.info("GET /user..." + principal);
//	}
	
	@GetMapping("/user")	
	public void user() {
		log.info("GET /user...");
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		log.info("Authentication : "+authentication);
		
		
	}
	
	@GetMapping("/manager")
	public void manager() {
		log.info("GET /manager..");	
	}
	
	@GetMapping("/admin")
	public void admin() {
		log.info("GET /admin..");	
	}	
	
	
	@GetMapping("/join")
	public void join() {
		log.info("GET /join...");
	}

	@PostMapping("/join")
	public String join_post(@Valid UserDto dto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		log.info("POST /join..." + dto);
		if (bindingResult.hasErrors()) {
			for (FieldError error : bindingResult.getFieldErrors()) {
				log.info("ERROR field : " + error.getField() + "error message : " + error.getDefaultMessage());
				model.addAttribute(error.getField(), error.getDefaultMessage());				
			}
			return "/join";
		}
		
		boolean isJoin = userService.userJoin(dto);
		if (isJoin) {
			redirectAttributes.addFlashAttribute("message","회원가입 완료");
			return "redirect:/login";
		} else {
			return "forward:/join";
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
