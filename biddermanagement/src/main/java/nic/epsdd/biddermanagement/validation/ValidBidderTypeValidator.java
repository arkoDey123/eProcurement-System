package nic.epsdd.biddermanagement.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import nic.epsdd.biddermanagement.dto.BidderEnrollmentDto;

public class ValidBidderTypeValidator implements ConstraintValidator<ValidBidderType, BidderEnrollmentDto> {

    @Override
    public boolean isValid(BidderEnrollmentDto dto, ConstraintValidatorContext context) {
        if (dto.getBidderType() == null) {
            return true; // Optionally handle null bidderType if needed
        }

        if (dto.getBidderType().equals(1)) { // Indian
            if (dto.getStateId() == null || dto.getPanNumber() == null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("State ID and PAN number are required for Indian bidders.")
                        .addConstraintViolation();
                return false;
            }
            if (dto.getCountryId() != null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Country ID must not be set for Indian bidders.")
                        .addConstraintViolation();
                return false;
            }
        } else if (dto.getBidderType().equals(2)) { // Foreign
            if (dto.getCountryId() == null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Country ID is required for foreign bidders.")
                        .addConstraintViolation();
                return false;
            }
            if (dto.getStateId() != null || dto.getPanNumber() != null) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("State ID and PAN number must not be set for foreign bidders.")
                        .addConstraintViolation();
                return false;
            }
        } else {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Bidder type must be either 1 (Indian) or 2 (Foreign).")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
