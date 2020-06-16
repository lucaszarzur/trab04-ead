package br.com.utfpr.libraryfive.validation;

import javax.validation.*;

import br.com.utfpr.libraryfive.service.UserService;
import br.com.utfpr.libraryfive.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserService userService;

    public void initialize(UniqueEmail uniqueEmail) {
        // auto-generated method, ignore
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return validateEmail(value);
    }

    private boolean validateEmail(String value) {
        UserModel user = userService.findByEmail(value);
        if (user == null)
            return true;
        return false;
    }
}