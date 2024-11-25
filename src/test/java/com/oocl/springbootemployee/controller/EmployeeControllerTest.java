package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.repository.EmployeeRepository;
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
class EmployeeControllerTest {

    @Autowired
    private MockMvc client;

    @Autowired
    private JacksonTester<Employee> employeeJacksonTester;

    @Autowired
    private JacksonTester<List<Employee>> employeesListJacksonTester;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void should_return_employees_when_get_all_given_emplpyees() throws Exception {
        // Given
        final List<Employee> employeeList = employeeRepository.getAll();

        String employeeListJson = employeesListJacksonTester.write(employeeList).getJson();

        // When & Then
        client.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers.content().json(employeeListJson));

    }

    @Test
    void should_return_employee_when_get_employee_by_id() throws Exception {
        // Given
        final Employee employee = employeeRepository.getById(3);
        int id = employee.getId();

        String employeeJson = employeeJacksonTester.write(employee).getJson();

        // When & Then
        client.perform(MockMvcRequestBuilders.get("/employees/" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
                .andExpect(MockMvcResultMatchers.content().json(employeeJson));

    }

}

