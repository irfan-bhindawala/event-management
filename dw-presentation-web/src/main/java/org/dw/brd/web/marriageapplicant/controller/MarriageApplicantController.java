package org.dw.brd.web.marriageapplicant.controller;

import java.util.List;

import org.dw.brd.persistence.entity.MarriageApplicant;
import org.dw.brd.service.MarriageApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marriage-applicants")
public class MarriageApplicantController {
	
	@Autowired
	private MarriageApplicantService marriageApplicantService;
	
	@RequestMapping(method = RequestMethod.GET)
	public List<MarriageApplicant> getAllApplicant(){
		return marriageApplicantService.getAllMarriageApplication();
	}
}
