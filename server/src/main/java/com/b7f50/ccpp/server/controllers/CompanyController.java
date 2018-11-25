package com.b7f50.ccpp.server.controllers;

import com.b7f50.ccpp.server.services.CompanyService;
import com.b7f50.ccpp.server.dtos.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    private static final long GIGA = 1000000000;

    @GetMapping("/companies")
    public List<Company> list(@RequestParam String name) {
        return companyService.find(name);
    }

    @GetMapping("/companies/{id}")
    public Company get(@PathVariable String id) {
        log.warn("got id: " + id);
        Company company = companyService.get(Long.parseLong(id));
        company.setQuota(company.getQuota() / GIGA);
        return company;
    }

    @PostMapping("/companies")
    public Company post(@RequestBody Company company) {
        log.warn("got company: " + company);
        company.setQuota(company.getQuota() * GIGA);
        return companyService.save(company);
    }
}
