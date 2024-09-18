package nic.epsdd.biddermanagement.service.impl;

import lombok.extern.slf4j.Slf4j;
import nic.epsdd.biddermanagement.dto.BidderEnrollmentDto;
import nic.epsdd.biddermanagement.entity.GepCorporateTenderer;
import nic.epsdd.biddermanagement.entity.GepCountryMaster;
import nic.epsdd.biddermanagement.entity.GepUser;
import nic.epsdd.biddermanagement.exception.BidderException;
import nic.epsdd.biddermanagement.repository.GepCorporateTendererRepository;
import nic.epsdd.biddermanagement.repository.GepCountryMasterRepository;
import nic.epsdd.biddermanagement.repository.GepUserRepository;
import nic.epsdd.biddermanagement.service.BidderEnrollmentService;
import nic.epsdd.biddermanagement.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
public class BidderEnrollmentServiceImpl implements BidderEnrollmentService {
    private final CommonUtil commonUtil;

    private final GepUserRepository gepUserRepository;
    private final GepCorporateTendererRepository gepCorporateTendererRepository;


    private final GepCountryMasterRepository gepCountryMasterRepository;

    @Autowired
    public BidderEnrollmentServiceImpl(CommonUtil commonUtil, GepUserRepository gepUserRepository, GepCorporateTendererRepository gepCorporateTendererRepository, GepCountryMasterRepository gepCountryMasterRepository) {
        this.commonUtil = commonUtil;
        this.gepUserRepository = gepUserRepository;
        this.gepCorporateTendererRepository = gepCorporateTendererRepository;
        this.gepCountryMasterRepository = gepCountryMasterRepository;
    }

    @Transactional
    @Override
    public BidderEnrollmentDto registerBidderEnrollment(BidderEnrollmentDto bidderEnrollmentDto) throws BidderException {
        // Check if login ID already exists
        if (gepUserRepository.existsByLoginId(bidderEnrollmentDto.getLoginId())) {
            throw new BidderException("Login Id already exists");
        }


        // Create and save GepUser
        GepUser gepUser = new GepUser();
        gepUser.setId(commonUtil.generateUniqueId());
        gepUser.setLoginId(bidderEnrollmentDto.getLoginId());
        gepUser.setPassword("encrypted-password");
        gepUser.setUserStatus("corporate");
        gepUser.setCreatedDate(LocalDateTime.now().truncatedTo(ChronoUnit.MILLIS));
        gepUser.setDisplayName(bidderEnrollmentDto.getContactName());
        gepUser.setMobileNo(bidderEnrollmentDto.getMobileNumber());
        gepUser.setAlternateEmailId(bidderEnrollmentDto.getCorrespondenceEmail());

        GepCountryMaster byPhoneIsdCode = this.gepCountryMasterRepository.findByPhoneIsdCode(bidderEnrollmentDto.getMobileId());

        gepUser.setMobileIsdCodeId(byPhoneIsdCode.getId());
        gepUser.setUserStatus("On Process");

        // Save GepUser and auto-generate the id
        gepUser = gepUserRepository.save(gepUser);

        // Create and save GepCorporateTenderer
        GepCorporateTenderer gepCorporateTenderer = new GepCorporateTenderer();
        gepCorporateTenderer.setId(commonUtil.generateUniqueId());
        gepCorporateTenderer.setUserId(gepUser.getId()); // Link the saved GepUser's ID
        gepCorporateTenderer.setCompany(bidderEnrollmentDto.getCompanyName());
        gepCorporateTenderer.setRegisteredAddress(bidderEnrollmentDto.getRegisteredAddress());
        gepCorporateTenderer.setEstablishedYear(bidderEnrollmentDto.getEstablishmentYear());
        gepCorporateTenderer.setBusinessNature(bidderEnrollmentDto.getNatureOfBusiness());
        gepCorporateTenderer.setLegalStatus(bidderEnrollmentDto.getLegalStatus());
        gepCorporateTenderer.setTitle(bidderEnrollmentDto.getTitle());
        gepCorporateTenderer.setContactName(bidderEnrollmentDto.getContactName());
        gepCorporateTenderer.setDesignation(bidderEnrollmentDto.getDesignation());
        gepCorporateTenderer.setPhoneIsdCodeId(bidderEnrollmentDto.getPhoneIsdCodeId());
        gepCorporateTenderer.setPhoneStdCode(bidderEnrollmentDto.getPhoneStdCode());
        gepCorporateTenderer.setPhone(bidderEnrollmentDto.getPhoneNumber());

        // Set bidder type-specific fields based on bidderType (1 for Indian, 2 for Foreign)
        Integer bidderType = bidderEnrollmentDto.getBidderType();
        if (bidderType == 1) {  // Indian Bidder
            gepCorporateTenderer.setPanNumber(bidderEnrollmentDto.getPanNumber());
            gepCorporateTenderer.setStateId(bidderEnrollmentDto.getStateId());
        } else if (bidderType == 2) {  // Foreign Bidder
            gepCorporateTenderer.setCountryId(bidderEnrollmentDto.getCountryId());
        } else {
            throw new IllegalArgumentException("Bidder type must be either '1' (Indian) or '2' (Foreign). Provided value: " + bidderType);
        }

        gepCorporateTenderer.setDateOfBirth(bidderEnrollmentDto.getDateOfBirth());
        gepCorporateTenderer.setBidderCategory(bidderEnrollmentDto.getCompanyCategory());
        gepCorporateTenderer.setRegNumber(bidderEnrollmentDto.getRegistrationNumber());
        gepCorporateTenderer.setCity(bidderEnrollmentDto.getCity());
        gepCorporateTenderer.setPostalCode(bidderEnrollmentDto.getPostalCode());

        // Check if preferential bidder is true
        if (Boolean.TRUE.equals(bidderEnrollmentDto.getPreferentialBidder())) {
            gepCorporateTenderer.setIsPrivilegedBidder(true);

            // Check if preference category ID is provided
            if (bidderEnrollmentDto.getPreferenceCategoryId() == null) {
                throw new IllegalArgumentException("Preference category must be selected if preferential bidder is true.");
            }

            gepCorporateTenderer.setPrivilegeMasterId(bidderEnrollmentDto.getPreferenceCategoryId());
        } else {
            gepCorporateTenderer.setIsPrivilegedBidder(false);
        }

        // Set mobile ISD code
        gepCorporateTenderer.setMobileIsdCode(String.valueOf(bidderEnrollmentDto.getMobileId()));

        // Save GepCorporateTenderer
        gepCorporateTendererRepository.save(gepCorporateTenderer);

        log.info("Bidder enrollment registered successfully for userId: {}", gepUser.getId());
        return bidderEnrollmentDto;
    }
}
