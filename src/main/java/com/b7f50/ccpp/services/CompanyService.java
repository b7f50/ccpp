package com.b7f50.ccpp.services;

import com.b7f50.ccpp.dtos.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public long count() {
        return jdbcTemplate.queryForObject("SELECT count(1) FROM company", Long.class);
    }

    public List<Company> find(String name) {
        return jdbcTemplate.query(
                "SELECT id, name FROM company WHERE name like ?",
                new Object[]{name + "%"},
                (rs, rowNum) -> new Company(rs.getLong("id"), rs.getString("name"))
        );
    }
}
