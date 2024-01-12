package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.services.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService service;

	@PostMapping("/register")
	public String addUsers(@ModelAttribute User user) {
		boolean userstatus = service.emailExists(user.getEmail());
		if (userstatus == false) {
			service.addUser(user);
			System.out.println("User Added");
		} else {
			System.out.println("User already exists");
		}
		service.addUser(user);
		return "home";

	}

	@PostMapping("/validate")
	public String validate(@RequestParam("email") String email,
			@RequestParam("password") String password,HttpSession session) {
		if(service.validateUser(email, password)== true) {
			String role= service.getRole(email);
			session.setAttribute("email", email);
			if(role.equals("admin")) {
				
				return "AdminHome";
			}
			else
			{
			return "CustomerHome";
			}
		}
		else {
			return "login";
		}
		}

	/*@GetMapping("/pay")
	public String pay(@RequestParam String email) {

		boolean paymentStatus = false;
		if (paymentStatus == true) {
			User user = service.getUser(email);
			user.setPremium(true);
		}
		return "login";

	}*/
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		
		session.invalidate();
		return "login";
	}
}