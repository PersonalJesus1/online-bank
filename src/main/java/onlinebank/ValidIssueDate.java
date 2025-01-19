package onlinebank;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = IssueDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidIssueDate {
    String message() default "The issue date must not be earlier than 2022.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

