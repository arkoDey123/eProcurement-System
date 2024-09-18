package nic.epsdd.biddermanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nic.epsdd.biddermanagement.validation.ValidBidderType;
import nic.epsdd.biddermanagement.validation.ValidPreferenceCategory;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ValidBidderType
@ValidPreferenceCategory
public class BidderEnrollmentDto {

    // * ------------------- Login and Contact Details -------------------
    private Long id;

    @NotBlank(message = "Login ID is required. -> (e.g., abc@xyz.com ).")
    @Pattern(regexp = "^[\\S]+@[\\S]+\\.[\\S]+$", message = "Login ID must be a valid email address and must not contain spaces.")
    @Email(message = "Invalid Login ID format. Please follow the format instructions.")
    private String loginId;

    @NotBlank(message = "Correspondence email address is required.")
    @Pattern(regexp = "^[\\S]+@[\\S]+\\.[\\S]+$", message = "Email must be a valid format and must not contain spaces.")
    @Email(message = "Invalid email format. Please provide a valid email address.")
    private String correspondenceEmail;

    private String mobileId;

    @NotNull(message = "Mobile number is required.")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be exactly 10 digits and must not contain spaces.")
    @Size(min = 10, max = 10, message = "Mobile number must be exactly 10 digits.")
    private String mobileNumber;

    // * ------------------- Company Details -------------------
    @NotBlank(message = "Company name is required.")
    private String companyName;

    // # ------------------- Preferential Bidder -------------------
    private Boolean preferentialBidder;

    private Long preferenceCategoryId;

    // ------------------- Company Legal Details -------------------
    @NotBlank(message = "Registration number is required.")
    @Size(max = 50, message = "Registration number must be less than or equal to 50 characters.")
    private String registrationNumber;

    @NotBlank(message = "Registered address is required.")
    @Size(max = 100, message = "Registered address must be less than or equal to 100 characters.")
    private String registeredAddress;

    @NotBlank(message = "Information on partners or directors is required.")
    @Size(max = 100, message = "Partners/Directors information must be less than or equal to 100 characters.")
    private String partners_Directors;

    // ------------------- Bidder Type -------------------
    @Min(value = 1, message = "Bidder type must be 1 (Indian) or 2 (Foreign).")
    @Max(value = 2, message = "Bidder type must be 1 (Indian) or 2 (Foreign).")
    private Integer bidderType;

    // 2 (Foreign).
    private Long stateId;

    // 1 (Indian)
    @Size(max = 10, message = "PAN number must be less than or equal to 10 characters.")
    @Pattern(regexp = "[A-Z]{5}[0-9]{4}[A-Z]", message = "Invalid PAN number format. The correct format is ABCDE1234F.")
    private String panNumber;

    private Long countryId;

    // ------------------- Common Fields for Both Indian and Foreign Bidders -------------------
    @NotBlank(message = "City name is required.")
    @Size(max = 100, message = "City name must be less than or equal to 100 characters.")
    private String city;

    @NotBlank(message = "Postal code is required.")
    @Pattern(regexp = "^\\d{6}$", message = "Postal code must be a valid 6-digit number.")
    private String postalCode;

    private Integer establishmentYear;

    @NotBlank(message = "Nature of business is required.")
    @Size(max = 100, message = "Nature of business must be less than or equal to 100 characters.")
    private String natureOfBusiness;

    @NotBlank(message = "Legal status of the company is required.")
    private String legalStatus;

    @NotBlank(message = "Company category is required.")
    private String companyCategory;

    // ------------------- Company’s Contact Person Details -------------------
    @NotBlank(message = "Title is required.")
    private String title;

    @NotBlank(message = "Contact person's name is required.")
    @Size(max = 100, message = "Contact person's name must be less than or equal to 100 characters.")
    private String contactName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Designation is required for the contact person.")
    @Size(max = 50, message = "Designation must be less than or equal to 50 characters.")
    private String designation;

    private Long phoneIsdCodeId;

    @NotBlank(message = "Phone STD code is required.")
    @Size(min = 2, max = 8, message = "STD Code must be between 2 and 8 digits.")
    private String phoneStdCode;

    @NotBlank(message = "Phone number is required.")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be a valid 10-digit number.")
    private String phoneNumber;

    // ------------------- Bidder Role -------------------
    @Column(name = "bidder_role", nullable = false)
    private String bidderRole;

    @PrePersist
    private void prePersistBidderRole() {
        if (this.bidderRole == null) {
            this.bidderRole = "Bidder";
        }
    }
}
