package com.b7f50.ccpp.server.controllers;

import com.b7f50.ccpp.server.services.CompanyService;
import com.b7f50.ccpp.server.dtos.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> list() {
        return companyService.findAll();
    }

    @GetMapping("/companies/{id}")
    public Company get(@PathVariable String id) {
        return companyService.get(id);
    }
}
