package org.dw.brd.eventmanagement.marriageapplicant.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.commons.lang3.RandomStringUtils;
import org.dw.brd.ApplicationBootStart;
import org.dw.brd.persistence.entity.MarriageApplicant;
import org.dw.brd.persistence.repository.MarriageApplicantRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ApplicationBootStart.class)
@AutoConfigureMockMvc
public class MarriageApplicantControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private MarriageApplicantRepository repository;

    @Test
    @Ignore
    public void getAllApplicants() throws Exception {
        loadTestApplicant(3);

        mvc.perform(get("/marriage-applicants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    @Ignore
    public void saveApplicant() throws Exception {
        MarriageApplicant ma = createMarriageApplicant();
        mvc.perform(post("/marriage-applicants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(ma)))
                .andExpect(status().isOk());
    }

    private void loadTestApplicant(int iterations) {
        for (; iterations > 0; iterations--) {
            MarriageApplicant ma = createMarriageApplicant();
            repository.save(ma);
        }
        repository.flush();
    }

    private MarriageApplicant createMarriageApplicant() {
        return MarriageApplicant.builder()
                .firstName(RandomStringUtils.randomAlphabetic(5))
                .lastName(RandomStringUtils.randomAlphabetic(5))
                .build();
    }

}