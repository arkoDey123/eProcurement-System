package nic.epsdd.biddermanagement.repository;

import nic.epsdd.biddermanagement.entity.GepLookupMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GepLookupMasterRepository extends JpaRepository<GepLookupMaster, Long> {
}
