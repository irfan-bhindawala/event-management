package org.dw.brd.eventmanagement.web.marriageapplicant.controller;


import org.dw.brd.eventmanagement.persistence.entity.MarriageApplicant;
import org.dw.brd.eventmanagement.service.MarriageApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("marriage-applicants")
public class MarriageApplicantController {

    private final MarriageApplicantService marriageApplicantService;

    @Autowired
    public MarriageApplicantController(MarriageApplicantService service) {
        this.marriageApplicantService = service;
    }

    @GetMapping
    public ResponseEntity<List<MarriageApplicant>> getAllApplicants() {
        return  new ResponseEntity<>(marriageApplicantService.findAllApplicants(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> createApplicant(MarriageApplicant marriageApplicant) {
        Long applicantId =  marriageApplicantService.createApplicant(marriageApplicant);
        return new ResponseEntity<>(applicantId, HttpStatus.OK);
    }
}
