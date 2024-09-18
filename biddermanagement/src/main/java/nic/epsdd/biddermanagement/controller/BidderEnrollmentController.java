package nic.epsdd.biddermanagement.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import nic.epsdd.biddermanagement.dto.BidderEnrollmentDto;
import nic.epsdd.biddermanagement.exception.BidderException;
import nic.epsdd.biddermanagement.service.BidderEnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/v1/bidder-enrollment")
public class BidderEnrollmentController {

    private final BidderEnrollmentService bidderEnrollmentService;

    // Constructor-based dependency injection (recommended)
    public BidderEnrollmentController(BidderEnrollmentService bidderEnrollmentService) {
        this.bidderEnrollmentService = bidderEnrollmentService;
    }

    @PostMapping("/register") //  http://localhost:2000/api/v1/bidder-enrollment/register
    public ResponseEntity<BidderEnrollmentDto> registerBidderEnrollment(@RequestBody @Valid BidderEnrollmentDto bidderEnrollmentDto) throws BidderException {
        log.info("Received request to register a new bidder: {}", bidderEnrollmentDto);

        // Call service to handle the registration process
        BidderEnrollmentDto createdBidder = bidderEnrollmentService.registerBidderEnrollment(bidderEnrollmentDto);

        // Return response with CREATED status and the created bidder information
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBidder);
    }
}
