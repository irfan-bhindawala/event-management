package org.dw.brd.eventmanagement.service;



import org.dw.brd.eventmanagement.persistence.entity.MarriageApplicant;

import java.util.List;

public interface MarriageApplicantService {

    List<MarriageApplicant> findAllApplicants();

    Long createApplicant(MarriageApplicant applicant) throws PartnerNotFoundException;
}
