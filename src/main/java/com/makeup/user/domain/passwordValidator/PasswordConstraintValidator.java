package com.makeup.user.domain.passwordValidator;

import com.makeup.user.domain.exceptions.PasswordConstraintException;
import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import static com.makeup.user.domain.exceptions.PasswordConstraintException.CAUSE.PASSWORD_IS_BLANK;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        if (password == null) {
            throw new PasswordConstraintException(PASSWORD_IS_BLANK);
        }

        PasswordValidator passwordValidator = new PasswordValidator(
                new WhitespaceRule(),
                new LengthRule(6, 15),
                new CharacterRule(EnglishCharacterData.Special, 1),
                new CharacterRule(EnglishCharacterData.Digit, 1),
                new CharacterRule(EnglishCharacterData.UpperCase, 1)
        );

        RuleResult result = passwordValidator.validate(new PasswordData(password));

        if (result.isValid()) {
            return true;
        }
        passwordValidator.getMessages(result)
                .forEach(PasswordConstraintException::new);
        return false;
    }
}