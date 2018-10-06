package com.b7f50.ccpp.controllers;

import com.b7f50.ccpp.services.CompanyService;
import com.b7f50.ccpp.dtos.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> find(@RequestParam(name = "name", required = false) String name) {
        if (name != null) {
            return companyService.find(name);
        }
        return null;
    }
}
