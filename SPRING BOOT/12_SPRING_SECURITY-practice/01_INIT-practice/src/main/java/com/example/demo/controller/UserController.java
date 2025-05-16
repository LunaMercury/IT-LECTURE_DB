package com.example.demo.controller;


import com.example.demo.domain.dto.UserDto;
import com.example.demo.domain.entity.User;
import com.example.demo.domain.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
    public void user(Model model) {
        log.info("GET /user...");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication : " + authentication);
        model.addAttribute("auth",authentication);

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
    public String join_post(UserDto dto, Model model, RedirectAttributes redirectAttributes) {
        log.info("POST /join..." + dto);

        // DTO -> ENTITY (DB 저장), ENTITY -> DTO (뷰로 전달)
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(dto.toEntity());

        boolean isJoin = true;
        if (isJoin) {
            redirectAttributes.addFlashAttribute("message", "회원가입 완료");
            return "redirect:/login";
        } else {
            return "join";
        }
    }
}
