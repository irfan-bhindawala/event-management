package org.dw.brd.service;

import java.util.Arrays;
import java.util.List;

import org.dw.brd.persistence.entity.MarriageApplicant;
import org.dw.brd.persistence.repository.MarriageApplicantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarriageApplicantServiceImpl implements MarriageApplicantService {

	@Autowired
	private MarriageApplicantRepository marriageApplicantRepository;
	
	@Override
	public List<MarriageApplicant> getAllMarriageApplication() {
		
		return Arrays.asList(new MarriageApplicant(1L, "First", "Last"));
//		return marriageApplicantRepository.findAll();
	}

}
