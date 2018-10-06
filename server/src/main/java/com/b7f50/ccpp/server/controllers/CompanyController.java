package com.b7f50.ccpp.server.controllers;

import com.b7f50.ccpp.server.services.CompanyService;
import com.b7f50.ccpp.server.dtos.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
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
