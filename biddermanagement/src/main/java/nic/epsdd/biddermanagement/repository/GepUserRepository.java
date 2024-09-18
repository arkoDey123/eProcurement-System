package nic.epsdd.biddermanagement.repository;

import nic.epsdd.biddermanagement.entity.GepUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GepUserRepository extends JpaRepository<GepUser, Long> {
    boolean existsByLoginId(String loginId);
}
