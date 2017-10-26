package org.dw.brd.eventmanagement.web.marriageapplicant.controller;


import org.dw.brd.eventmanagement.persistence.entity.MarriageApplicant;
import org.dw.brd.eventmanagement.service.MarriageApplicantService;
import org.dw.brd.eventmanagement.service.PartnerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //TODO: Check if it is appropriate to have ResponseEntity<Object> as return type.
    // What are possible alternatives?
    @PostMapping
    public ResponseEntity<Object> createApplicant(@RequestBody MarriageApplicant marriageApplicant) {
        Long applicantId;
        try {
            applicantId = marriageApplicantService.createApplicant(marriageApplicant);
        } catch (PartnerNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(applicantId, HttpStatus.OK);
    }
}
