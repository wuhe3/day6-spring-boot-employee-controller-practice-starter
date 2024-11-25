package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
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

    @BeforeEach
    void setUp() {
        employeeRepository.getAll().clear();
        employeeRepository.createEmployee(new Employee(0, "Mary", 20, Gender.FEMALE, 8000.0));
        employeeRepository.createEmployee(new Employee(0, "Amy", 15, Gender.FEMALE, 7000.0));
        employeeRepository.createEmployee(new Employee(0, "Micky", 19, Gender.MALE, 5000.0));
        employeeRepository.createEmployee(new Employee(0, "Tony", 19, Gender.MALE, 4000.0));
        employeeRepository.createEmployee(new Employee(0, "Jenny", 19, Gender.FEMALE, 5000.0));
        employeeRepository.createEmployee(new Employee(0, "John", 19, Gender.MALE, 19.0));
    }

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
        client.perform(MockMvcRequestBuilders.get("/employees/{id}", id))
                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
                .andExpect(MockMvcResultMatchers.content().json(employeeJson));

    }

    @Test
    void should_return_male_when_get_by_male_given_gender_is_male() throws Exception {
        // Given
        final List<Employee> employeeList = employeeRepository.getByGender(Gender.MALE);
        String employeeListJson = employeesListJacksonTester.write(employeeList).getJson();

        // When & Then
        client.perform(MockMvcRequestBuilders.get("/employees")
                        .param(("gender"), "MALE")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(employeeListJson));
    }

    @Test
    void should_return_created_employee_when_create_employee() throws Exception {
        // Given
        String employeeBody = "    {\n" +
                "        \"name\": \"tom\",\n" +
                "        \"age\": 20,\n" +
                "        \"gender\": \"FEMALE\",\n" +
                "        \"salary\": 8000.0\n" +
                "    }";

        // When & Then
        client.perform(MockMvcRequestBuilders.post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeBody)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());
    }

    @Test
    void should_update_employee_when_update_employee() throws Exception {
        // Given
        int id = 1;
        Employee employee = employeeRepository.getById(id);
        employee.setAge(18);
        employee.setSalary(7000.0);
        String employeeJson = employeeJacksonTester.write(employee).getJson();

        // When & Then
        client.perform(MockMvcRequestBuilders.put("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(employeeJson));
    }

    @Test
    void should_delete_and_return_204_status_when_delete_employee() throws Exception {
        // Given
        int id = 2;

        // When & Then
        client.perform(MockMvcRequestBuilders.delete("/employees/" + id))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

//    @Test
//    void should_return_5_employees_when_get_all_given_page_1_and_page_size_5() throws Exception {
//        // Given
//        final List<Employee> employeeList = employeeRepository.getByPageAndSize(1, 5);
//        String employeeListJson = employeesListJacksonTester.write(employeeList).getJson();
//
//        // When & Then
//        client.perform(MockMvcRequestBuilders.get("/employees")
//                        .param("page", "1")
//                        .param("pageSize", "5")
//                )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(5)))
//                .andExpect(MockMvcResultMatchers.content().json(employeeListJson));
//    }

//    @Test
//    void should_return_5_employees_when_get_all_given_page_1_and_page_size_5() throws Exception {
//        // Given
//        final List<Employee> employeeList = employeeRepository.getByPageAndSize(2, 2);
//        String employeeListJson = employeesListJacksonTester.write(employeeList).getJson();
//
//        // When & Then
//        client.perform(MockMvcRequestBuilders.get("/employees")
//                        .param("page", "2")
//                        .param("pageSize", "2")
//                )
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
//                .andExpect(MockMvcResultMatchers.content().json(employeeListJson));
//    }

}

