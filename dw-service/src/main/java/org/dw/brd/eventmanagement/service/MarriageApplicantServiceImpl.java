package org.dw.brd.eventmanagement.service;

import org.dw.brd.eventmanagement.persistence.entity.MarriageApplicant;
import org.dw.brd.eventmanagement.persistence.repository.MarriageApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarriageApplicantServiceImpl implements MarriageApplicantService {

    private MarriageApplicantRepository marriageApplicantRepository;

    @Autowired
    public MarriageApplicantServiceImpl(MarriageApplicantRepository repo) {
        this.marriageApplicantRepository = repo;
    }

    @Override
    public List<MarriageApplicant> findAllApplicants() {
        return marriageApplicantRepository.findAll();
    }

    @Override
    public Long createApplicant(MarriageApplicant applicant) {
        MarriageApplicant saved =  marriageApplicantRepository.saveAndFlush(applicant);
        return saved.getId();
    }
}
