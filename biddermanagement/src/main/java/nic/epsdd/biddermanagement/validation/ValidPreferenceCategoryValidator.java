package nic.epsdd.biddermanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nic.epsdd.biddermanagement.dto.BidderEnrollmentDto;

public class ValidPreferenceCategoryValidator implements ConstraintValidator<ValidPreferenceCategory, BidderEnrollmentDto> {

    @Override
    public boolean isValid(BidderEnrollmentDto dto, ConstraintValidatorContext context) {
        if (dto.getPreferentialBidder() != null) {
            if (dto.getPreferentialBidder()) { // Preferential Bidder is true
                if (dto.getPreferenceCategoryId() == null) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("Preference Category ID must be provided for preferential bidders.")
                            .addConstraintViolation();
                    return false;
                }
            } else { // Preferential Bidder is false
                if (dto.getPreferenceCategoryId() != null) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("Preference Category ID must not be set for non-preferential bidders.")
                            .addConstraintViolation();
                    return false;
                }
            }
        }
        return true;
    }
}
