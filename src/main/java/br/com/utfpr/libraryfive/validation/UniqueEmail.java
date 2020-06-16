package br.com.utfpr.libraryfive.validation;

import java.lang.annotation.*;
import javax.validation.*;

import static java.lang.annotation.ElementType.*;

@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
public @interface UniqueEmail {

    public String message() default "There is already user with this email!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};

}