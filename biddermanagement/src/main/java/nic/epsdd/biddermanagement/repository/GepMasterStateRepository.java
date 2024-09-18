package nic.epsdd.biddermanagement.repository;

import nic.epsdd.biddermanagement.entity.GepMasterState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GepMasterStateRepository extends JpaRepository<GepMasterState, Long> {
}