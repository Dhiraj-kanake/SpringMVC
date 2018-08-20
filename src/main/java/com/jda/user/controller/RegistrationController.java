package com.jda.user.controller;

import com.jda.user.model.User;
import com.jda.user.service.MailService;
import com.jda.user.service.UserService;
import com.jda.user.validator.UserValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class RegistrationController {
	@Autowired
	public UserService userService;
	@Autowired
	UserValidator userValidator;
	@Autowired
	//private MailService mailService; 
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("register");
		mav.addObject("user", new User());
		
		return mav;
	}
	@RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
	                            @ModelAttribute("user") User user,BindingResult result) throws IOException {
		userValidator.validate(user, result);
		if(result.hasErrors())
			return new  ModelAndView("register");
		else{
		userService.register(user);
		
		response.sendRedirect("login");
		}
		return null;
	}
}
