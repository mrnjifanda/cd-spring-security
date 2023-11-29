package com.njifanda.ssa.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.njifanda.ssa.Models.User;
import com.njifanda.ssa.Services.UserService;

@Component
public class UserValidator implements Validator {

	@Autowired
	UserService userService;

    @Override
    public boolean supports(Class<?> c) {
        return User.class.equals(c);
    }

    @Override
    public void validate(Object object, Errors errors) {

        User user = (User) object;
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }
        
        User findUser = userService.findByUsername(user.getUsername());
        if (findUser != null) {
        	errors.rejectValue("username", "Exist");
        }
    }
}
