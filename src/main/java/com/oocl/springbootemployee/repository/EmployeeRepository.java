package com.oocl.springbootemployee.repository;

import com.oocl.springbootemployee.model.Employee;
import com.oocl.springbootemployee.model.Gender;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> employeeList = new ArrayList<>();

    public EmployeeRepository() {
        employeeList.add(new Employee(1, "Tom", 20, Gender.FEMALE, 8000.0));
        employeeList.add(new Employee(2, "Amy", 15, Gender.FEMALE, 7000.0));
        employeeList.add(new Employee(3, "Ben", 19, Gender.MALE, 5000.0));
    }

    public List<Employee> getAll() {
        return employeeList;
    }

}
