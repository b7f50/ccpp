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

    @GetMapping("/companies")
    public List<Company> list(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        return companyService.find(pageSize, pageNumber);
    }

    @GetMapping("/companies/{id}")
    public Company get(@PathVariable String id) {
        return companyService.get(id);
    }

    @PostMapping("/companies")
    public Company post(@RequestBody Company company) {
        log.warn("got company: " + company);
        return companyService.save(company);
    }
}
