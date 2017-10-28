package org.dw.brd.eventmanagement.persistence.repository;

import org.dw.brd.eventmanagement.persistence.entity.MarriageApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarriageApplicantRepository extends JpaRepository<MarriageApplicant, Long> {

    MarriageApplicant findById(Long id);
}
