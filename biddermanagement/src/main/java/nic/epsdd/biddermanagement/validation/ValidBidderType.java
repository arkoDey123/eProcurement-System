package nic.epsdd.biddermanagement.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidBidderTypeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidBidderType {
    String message() default "Invalid bidder type or related fields.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
