package com.club_matching.app.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.club_matching.app.domain.dto.UserDto;
import com.club_matching.app.domain.service.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class UserController {
	@Autowired
	private UserServiceImpl userService;

	@GetMapping("/join")
	public void join() {
		log.info("GET /join");
	}

	@PostMapping("/join")
	public String join_post(UserDto dto, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) throws SQLException {
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
			redirectAttributes.addFlashAttribute("message", "회원가입 완료");
			return "redirect:/login";
		} else {
			return "forward:/join";
		}
	}

}
