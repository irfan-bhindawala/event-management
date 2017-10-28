package org.dw.brd.eventmanagement.marriageapplicant.controller;

import org.assertj.core.util.Lists;
import org.dw.brd.eventmanagement.EventManagementApplication;
import com.google.gson.Gson;
import org.apache.commons.lang3.RandomStringUtils;
import org.dw.brd.eventmanagement.persistence.entity.MarriageApplicant;
import org.dw.brd.eventmanagement.persistence.repository.MarriageApplicantRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = EventManagementApplication.class)
@AutoConfigureMockMvc
public class MarriageApplicantControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    private MarriageApplicantRepository repository;

    @After
    public void clearData() {
        repository.deleteAll();
    }

    @Test
    public void whenGetAllApplicants_ThenReturnListOfMarriageApplicants() throws Exception {
        loadTestApplicant(3);

        mvc.perform(get("/marriage-applicants"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void whenPostApplicant_thenSaveApplicant() throws Exception {
        MarriageApplicant ma = createMarriageApplicant();
        mvc.perform(post("/marriage-applicants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(ma)))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void whenPostApplicantWithPartnerId_thenSaveApplicant() throws Exception {
        Long alreadyPresent = loadTestApplicant(1).get(0);
        MarriageApplicant ma = createMarriageApplicant();
        ma.setPartnerId(alreadyPresent);
        mvc.perform(post("/marriage-applicants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(ma)))
                .andExpect(status().isOk()).andDo(print());
    }

    @Test
    public void whenPostApplicantWithInvalidPartnerId_then404() throws Exception {
        MarriageApplicant ma = createMarriageApplicant();
        ma.setPartnerId(1L);
        mvc.perform(post("/marriage-applicants")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new Gson().toJson(ma)))
                .andExpect(status().isNotFound()).andDo(print());
    }

    private List<Long> loadTestApplicant(int iterations) {
        List<Long> maIds = Lists.newArrayList();
        for (; iterations > 0; iterations--) {
            MarriageApplicant ma = createMarriageApplicant();
            maIds.add(repository.save(ma).getId());
        }
        repository.flush();
        return maIds;
    }

    private MarriageApplicant createMarriageApplicant() {
        return MarriageApplicant.builder()
                .firstName(RandomStringUtils.randomAlphabetic(5))
                .lastName(RandomStringUtils.randomAlphabetic(5))
                .address(RandomStringUtils.randomAlphanumeric(10))
                .build();
    }

}