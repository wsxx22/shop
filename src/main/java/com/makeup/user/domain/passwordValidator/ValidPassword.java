package com.makeup.user.domain.passwordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target(value = ElementType.FIELD)
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "Correct password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}