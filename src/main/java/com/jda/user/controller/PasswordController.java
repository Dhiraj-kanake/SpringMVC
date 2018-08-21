package com.jda.user.controller;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jda.user.model.User;
import com.jda.user.service.UserService;
import com.jda.user.service.MailService;
@Controller
public class PasswordController {

	final static Logger logger = Logger.getLogger(LoginController.class); 
	
  @Autowired
  private UserService userService;

  @Autowired
  private MailService emailService;

  @Autowired
 // private BCryptPasswordEncoder bCryptPasswordEncoder;
	
  // Display forgotPassword page
  @RequestMapping(value = "/forgot", method = RequestMethod.GET)
  public ModelAndView displayForgotPasswordPage() {
    return new ModelAndView("ForgotPassword");
  }
    
  // Process form submission from forgotPassword page
  @RequestMapping(value = "/forgotProcess", method = RequestMethod.POST)
  public ModelAndView processForgotPasswordForm(ModelAndView modelAndView, @RequestParam("email") String userEmail, HttpServletRequest request) {

    // Lookup user in database by e-mail
    User user = userService.findByEmail(userEmail);

    if (user==null) {
      modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
    } else {	
      // Generate random 36-character string token for reset password 
      user.setToken(UUID.randomUUID().toString());

      // Save token to database
      userService.saveUser(user);

      String appUrl = request.getScheme() + "://" + request.getServerName()+":"+request.getServerPort()+ request.getServletContext().getContextPath();
			
      // Email message
      SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
      passwordResetEmail.setFrom("shravanbosu@gmail.com");
      passwordResetEmail.setTo(user.getEmail());
      passwordResetEmail.setSubject("Password Reset Request");
      passwordResetEmail.setText("To reset your password, click the link below:\n" + appUrl
        + "/reset?token=" + user.getToken());
			
      emailService.sendMail(passwordResetEmail);
     
      // Add success message to view
      modelAndView.addObject("successMessage", "A password reset link has been sent to " + userEmail);
    }
  
    modelAndView.setViewName("ForgotPassword");
    return modelAndView;
  }

  // Display form to reset password
  @RequestMapping(value = "/reset", method = RequestMethod.GET)
  public ModelAndView displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {
		
    User user = userService.findByToken(token);

    if (user!=null) 
    { 
   	 // Token found in DB
   	 modelAndView= new ModelAndView("resetPassword");
      modelAndView.addObject("token", token);
      
      if(logger.isDebugEnabled()){                         //log4j
			logger.debug("This is info : *************************************" + user);
		}
      return modelAndView;
    } 
    // Token not found in DB
    modelAndView= new ModelAndView("redirect:/login");
     modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
    return modelAndView;
  }

  // Process reset password form
  @RequestMapping(value = "/resetProcess", method = RequestMethod.POST)
  public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> map ) {

    // Find the user associated with the reset token
    User user = userService.findByToken(map.get("token"));

    if (user!=null) {
      User resetUser = user; 
      // Set new password	          
      resetUser.setPassword(map.get("Password"));

      // Set the reset token to null so it cannot be used again
      resetUser.setToken(null);

      // Save user
      userService.savePassword(resetUser);

      // In order to set a model attribute on a redirect, we must use RedirectAttributes
      //redir.addFlashAttribute("successMessage", "You have successfully reset your password.  You may now login.");

      modelAndView.setViewName("redirect:login");
      return modelAndView;
    } else {
      modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
      modelAndView.setViewName("redirect:login");	
    }
		
    return modelAndView;
  }
   
  // Going to reset page without a token redirects to login page
  @ExceptionHandler(MissingServletRequestParameterException.class)
  public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
    return new ModelAndView("redirect:login");
  }
}
