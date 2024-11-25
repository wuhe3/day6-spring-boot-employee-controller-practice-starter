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

    public Employee updateEmployee(Employee employee) {
        return employeeList.stream()
                .filter(employee1 -> employee1.getId() == employee.getId())
                .findFirst()
                .map(employee1 -> {
                    employee1.setName(employee.getName());
                    employee1.setAge(employee.getAge());
                    employee1.setGender(employee.getGender());
                    employee1.setSalary(employee.getSalary());
                    return employee1;
                })
                .orElseThrow();
    }

    public void deleteEmployee(int id) {
        employeeList.removeIf(employee -> employee.getId() == id);
    }

    public List<Employee> getByPage(int page, int pageSize) {
        return employeeList.subList((page - 1) * pageSize, page * pageSize);
    }

    public void resetSequence() {
        sequence = 1;
    }
}
