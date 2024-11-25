package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Company;
import com.oocl.springbootemployee.repository.CompanyRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class CompanyControllerTest {
    @Autowired
    private MockMvc client;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private JacksonTester<List<Company>> companiesJacksonTester;

    @Test
    void should_return_companies_when_get_all_given_companies() throws Exception {
        // Given
        final List<Company> companies = companyRepository.getAll();

        String companiesJson = companiesJacksonTester.write(companies).getJson();

        // When & Then
        client.perform(MockMvcRequestBuilders.get("/companies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers.content().json(companiesJson));
    }

}
