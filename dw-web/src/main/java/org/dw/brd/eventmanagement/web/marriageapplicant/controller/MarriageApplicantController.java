package org.dw.brd.eventmanagement.web.marriageapplicant.controller;


import java.net.URI;
import java.util.List;

import org.dw.brd.eventmanagement.persistence.entity.MarriageApplicant;
import org.dw.brd.eventmanagement.service.MarriageApplicantService;
import org.dw.brd.eventmanagement.service.PartnerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<?> createApplicant(@RequestBody MarriageApplicant marriageApplicant) {
        try {
            marriageApplicantService.createApplicant(marriageApplicant);
        } catch (PartnerNotFoundException e) {
            return ResponseEntity.noContent().build();
        }
        URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(location).build();
    }
}
