package nic.epsdd.biddermanagement.repository;

import nic.epsdd.biddermanagement.entity.GepCountryMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GepCountryMasterRepository extends JpaRepository<GepCountryMaster, Long> {
    // Custom method to find all records by phoneIsdCode
    // Custom method to find GepCountryMaster by phoneIsdCode
    GepCountryMaster findByPhoneIsdCode(String phoneIsdCode);
}