package nic.epsdd.biddermanagement.repository;

import nic.epsdd.biddermanagement.entity.GepBidderPrivilegeMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GepBidderPrivilegeMasterRepository extends JpaRepository<GepBidderPrivilegeMaster, Long> {
}