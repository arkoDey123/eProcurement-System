package nic.epsdd.biddermanagement.util;

import lombok.extern.slf4j.Slf4j;
import nic.epsdd.biddermanagement.dto.BidderEnrollmentDto;
import nic.epsdd.biddermanagement.exception.BidderException;
import nic.epsdd.biddermanagement.repository.GepCorporateTendererRepository;
import nic.epsdd.biddermanagement.repository.GepUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
public class CommonUtil {

    private final GepUserRepository gepUserRepository;
    private final GepCorporateTendererRepository gepCorporateTendererRepository;

    @Autowired
    public CommonUtil(GepUserRepository gepUserRepository, GepCorporateTendererRepository gepCorporateTendererRepository) {
        this.gepUserRepository = gepUserRepository;
        this.gepCorporateTendererRepository = gepCorporateTendererRepository;
    }

    /**
     * Generate a unique ID for a bidder.
     *
     * @return a unique ID.
     */
    public Long generateUniqueId() throws BidderException {
        // Implementation for generating a unique ID
        Long uniqueId = ThreadLocalRandom.current().nextLong(10000, 100000);
        while (!isUniqueId(uniqueId)) {
            log.debug("Generated ID {} is not unique, generating a new one.", uniqueId);
            uniqueId = ThreadLocalRandom.current().nextLong(10000, 100000);
        }
        log.info("Generated unique ID: {}", uniqueId);
        return uniqueId;
    }

    /**
     * Check if the ID is unique in the database.
     *
     * @param id the ID to check.
     * @return true if the ID is unique, false otherwise.
     */
    public boolean isUniqueId(Long id) throws BidderException {
        boolean isUnique = gepUserRepository.findById(id).isEmpty() && gepCorporateTendererRepository.findById(id).isEmpty();
        if (isUnique) {
            log.debug("ID {} is unique.", id);
        } else {
            log.warn("ID {} already exists in the database.", id);
            throw new BidderException("ID {} already exists in the database");
        }
        return isUnique;
    }

    /**
     * Validate the details of the bidder enrollment.
     *
     * @param bidderEnrollmentDto the bidder enrollment details.
     * @throws BidderException if validation fails.
     */
    public void validateBidderEnrollment(BidderEnrollmentDto bidderEnrollmentDto) throws BidderException {
        boolean isPreferentialBidder = Boolean.TRUE.equals(bidderEnrollmentDto.getPreferentialBidder());
        Integer bidderType = bidderEnrollmentDto.getBidderType();

        // Validate preference category ID based on preferential bidder status
        if (isPreferentialBidder) {
            if (bidderEnrollmentDto.getPreferenceCategoryId() == null) {
                log.error("Preference Category ID is missing for preferential bidder.");
                throw new BidderException("Preference Category ID must be provided for preferential bidders.");
            }
        } else {
            if (bidderEnrollmentDto.getPreferenceCategoryId() != null) {
                log.error("Preference Category ID should not be set for non-preferential bidder.");
                throw new BidderException("Preference Category ID must not be set for non-preferential bidders.");
            }
        }

        // Validate state ID and PAN number based on bidder type
        if (Objects.equals(bidderType, 1)) {  // Indian Bidder
            if (bidderEnrollmentDto.getStateId() == null || bidderEnrollmentDto.getPanNumber() == null) {
                log.error("State ID and PAN number are required for Indian bidders.");
                throw new BidderException("State ID and PAN number are required for Indian bidders.");
            }
            if (bidderEnrollmentDto.getCountryId() != null) {
                log.error("Country ID must not be set for Indian bidders.");
                throw new BidderException("Country ID must not be set for Indian bidders.");
            }
        } else if (Objects.equals(bidderType, 2)) {  // Foreign Bidder
            if (bidderEnrollmentDto.getCountryId() == null) {
                log.error("Country ID is required for foreign bidders.");
                throw new BidderException("Country ID is required for foreign bidders.");
            }
            if (bidderEnrollmentDto.getStateId() != null || bidderEnrollmentDto.getPanNumber() != null) {
                log.error("State ID and PAN number must not be set for foreign bidders.");
                throw new BidderException("State ID and PAN number must not be set for foreign bidders.");
            }
        } else {
            log.error("Invalid bidder type: {}", bidderType);
            throw new BidderException("Invalid bidder type.");
        }
    }
}
