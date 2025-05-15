package com.epam.masterclass.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.masterclass.model.Insurance;
import com.epam.masterclass.service.InsuranceService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = InsuranceController.class)
public class InsuranceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InsuranceService insuranceService;

    @Autowired
    private ObjectMapper objectMapper;

    private UUID testId;
    private Insurance testInsurance;

    @BeforeEach
    void setUp() {
        testId = UUID.randomUUID();
        testInsurance = new Insurance();
        testInsurance.setId(testId);
        testInsurance.setPolicyNumber("POL-1234");
        testInsurance.setType("Health");
        testInsurance.setPremiumAmount(1000.0);
        testInsurance.setStartDate(LocalDate.now());
        testInsurance.setEndDate(LocalDate.now().plusYears(1));
        testInsurance.setInsuredPersonId(UUID.randomUUID());
        testInsurance.setStatus("Active");
    }

    @Test
    @WithMockUser
    public void shouldGetAllInsurances() throws Exception {
        List<Insurance> insurances = List.of(testInsurance);
        
        given(insuranceService.getAllInsurances()).willReturn(insurances);

        mockMvc.perform(get("/api/insurances")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].policyNumber", is(testInsurance.getPolicyNumber())));
    }

    @Test
    @WithMockUser
    public void shouldGetInsuranceById() throws Exception {
        given(insuranceService.getInsuranceById(any(UUID.class)))
                .willReturn(Optional.of(testInsurance));

        mockMvc.perform(get("/api/insurances/{id}", testId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.policyNumber", is(testInsurance.getPolicyNumber())));
    }

    @Test
    @WithMockUser
    public void shouldReturn404WhenInsuranceNotFound() throws Exception {
        given(insuranceService.getInsuranceById(any(UUID.class)))
                .willReturn(Optional.empty());

        mockMvc.perform(get("/api/insurances/{id}", UUID.randomUUID())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void shouldCreateInsurance() throws Exception {
        given(insuranceService.createInsurance(any(Insurance.class)))
                .willReturn(testInsurance);

        mockMvc.perform(post("/api/insurances")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testInsurance)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.policyNumber", is(testInsurance.getPolicyNumber())));
    }
}
