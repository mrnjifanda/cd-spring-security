package com.njifanda.ssa.Controllers;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.njifanda.ssa.Models.User;
import com.njifanda.ssa.Services.UserService;
import com.njifanda.ssa.validator.UserValidator;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	
	private UserService userService;
    private UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }
    
    @GetMapping(value = {"/", "/home"})
    public String home(Principal principal, Model model) {
        // 1
        String username = principal.getName();
        model.addAttribute("currentUser", userService.findByUsername(username));
        return "homePage";
    }
    

    @GetMapping("/admin")
    public String adminPage(Principal principal, Model model) {
        String username = principal.getName();
        User user = userService.findByUsername(username);
        System.out.print(user.getRoles());
        model.addAttribute("currentUser", user);
        return "adminPage";
    }
    

    @GetMapping("/register")
    public String registerForm(@Valid @ModelAttribute("user") User user) {
        return "auth/registrationPage";
    }
    
    @PostMapping("/register")
    public String registration(
    		@Valid @ModelAttribute("user") User user,
    		BindingResult result,
    		Model model,
    		HttpSession session
    ) {

    	userValidator.validate(user, result);
    	if (result.hasErrors()) {
            return "auth/registrationPage";
        }

    	userService.save(user, "USER");
//    	userService.save(user, "ADMIN");
        return "redirect:/login";
    }
    
    @GetMapping("/login")
    public String login(
    		@RequestParam(value="error", required=false) String error,
    		@RequestParam(value="logout", required=false) String logout,
    		Model model
    ) {

        if(error != null) {
            model.addAttribute("errorMessage", "Invalid Credentials, Please try again.");
        }

        if(logout != null) {
            model.addAttribute("logoutMessage", "Logout Successful!");
        }

        return "auth/loginPage";
    }
}
