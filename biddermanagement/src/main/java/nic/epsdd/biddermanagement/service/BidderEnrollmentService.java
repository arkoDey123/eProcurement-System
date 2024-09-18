package nic.epsdd.biddermanagement.service;

import jakarta.validation.Valid;
import nic.epsdd.biddermanagement.dto.BidderEnrollmentDto;
import nic.epsdd.biddermanagement.exception.BidderException;

public interface BidderEnrollmentService {
    BidderEnrollmentDto registerBidderEnrollment(@Valid BidderEnrollmentDto bidderEnrollmentDto) throws BidderException;
}
