package nic.epsdd.biddermanagement.repository;

import nic.epsdd.biddermanagement.entity.GepBidderCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GepBidderCategoryRepository extends JpaRepository<GepBidderCategory, Long> {
}
