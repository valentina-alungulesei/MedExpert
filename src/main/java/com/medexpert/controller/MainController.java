package com.medexpert.controller;

import java.util.List;
import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.medexpert.crm.CrmUser;
import com.medexpert.entity.Appointment;
import com.medexpert.entity.Test;
import com.medexpert.entity.User;
import com.medexpert.service.IUserService;

@Controller
public class MainController {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	private IUserService userService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}
	
	@Autowired // optional since we have only one constructor
	public MainController(IUserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String showIndexPage() {

		return "index";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@GetMapping("/login-error")
	public String showLoginErrorPage(Model model) {
		model.addAttribute("loginError", true);
		
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("crmUser", new CrmUser());
		
		return "registration-form";
	}
	
	@GetMapping("/processRegistrationForm")
	public String processRegistrationForm(
			@Valid @ModelAttribute("crmUser") CrmUser crmUser,
			BindingResult bindingResult,
			Model model) {
		
		String username = crmUser.getUsername();
		logger.info("Processing registration form for: " + username);
		
		// Form validation
		if (bindingResult.hasErrors()) {
			return "registration-form";
		}
		
		// Check the DB if user already exists
		User existingUser = userService.findByUsername(username);
		if (existingUser != null) {
//			model.addAttribute("crmUser", new CrmUser());
//			model.addAttribute("registrationError", "User name already exists."); // ?
			
			logger.warning("User name already exists.");
			return "registration-form";
		}
		
		// Create user account
		userService.save(crmUser);
		logger.info("Successfullly created user: " + username);
		
		return "registration-confirmation";
	}
	
	@GetMapping("/access-denied")
	public String showAccessDeniedPage() {
		return "access-denied";
	}
	
	@GetMapping("/home")
	public String showHomePage(Model model) {
		model.addAttribute("loggedInUser", userService.getLoggedInUser());
		model.addAttribute("testsList", userService.getTestList());
		model.addAttribute("appointment", new Appointment());
		
		return "home";
	}
	
	@GetMapping("/users")
	public String showUsersListPage(Model model) {
		List<User> users = userService.findAll();
		model.addAttribute("users", users);
		
		return "users-list";
	}
}
