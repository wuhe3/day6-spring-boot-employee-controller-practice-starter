package com.oocl.springbootemployee.controller;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import com.oocl.springbootemployee.repository.EmployeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;

    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable int id) {
        return employeeRepository.getById(id);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getByGender(@RequestParam Gender gender) {
        return employeeRepository.getByGender(gender);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable Integer id) {
        employee.setId(id);
        return employeeRepository.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable int id) {
        employeeRepository.deleteEmployee(id);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getByPage(@RequestParam int page, @RequestParam int pageSize) {
        return employeeRepository.getByPage(page, pageSize);
    }







}