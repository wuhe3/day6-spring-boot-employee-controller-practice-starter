package com.oocl.springbootemployee.repository;


import com.oocl.springbootemployee.model.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {
    private List<Company> companies = new ArrayList<>();

    public CompanyRepository() {
        this.companies.add(new Company(1, "OOCL"));
        this.companies.add(new Company(2, "COSCO"));
    }

    public List<Company> getAll() {
        return companies;
    }
}
