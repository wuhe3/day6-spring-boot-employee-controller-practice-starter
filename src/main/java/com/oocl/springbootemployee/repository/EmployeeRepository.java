package com.oocl.springbootemployee.repository;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private final List<Employee> employeeList = new ArrayList<>();

    private int sequence = 1;

    public EmployeeRepository() {
    }

    public List<Employee> getAll() {
        return employeeList;
    }

    public Employee getById(int id) {
        return employeeList.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElseThrow();
    }

    public List<Employee> getByGender(Gender gender) {
        return employeeList.stream()
                .filter(employee -> employee.getGender().toString().equals(gender.toString()))
                .collect(Collectors.toList());
    }

    public Employee createEmployee(Employee employee) {
        employee.setId(sequence);
        sequence +=1;
        employeeList.add(employee);
        return employee;
    }

}
