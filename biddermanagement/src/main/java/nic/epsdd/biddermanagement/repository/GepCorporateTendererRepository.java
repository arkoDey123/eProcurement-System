package nic.epsdd.biddermanagement.repository;

import nic.epsdd.biddermanagement.entity.GepCorporateTenderer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GepCorporateTendererRepository extends JpaRepository<GepCorporateTenderer, Long> {
}
