package nic.epsdd.biddermanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidPreferenceCategoryValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPreferenceCategory {
    String message() default "Invalid preference category.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
