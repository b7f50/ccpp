package com.b7f50.ccpp.server.services;

import com.b7f50.ccpp.server.dtos.Company;
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

    public List<Company> findAll() {

        String sql = "SELECT C.ID, C.NAME, CPP.EXPIRATION_DATE, CPP.IDENTITY_LIMIT, CPP.QUOTA_LIMIT " +
                "FROM COMPANY C " +
                "JOIN  COMPANY_PAYMENT_PACKAGE CPP ON C.COMPANY_PAYMENT_PACKAGE  = CPP.ID";

        return jdbcTemplate.query(
                sql,
                (rs, rowNum) -> new Company(rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getString("EXPIRATION_DATE"),
                        rs.getLong("IDENTITY_LIMIT"),
                        rs.getLong("QUOTA_LIMIT"))
        );
    }

    public Company get(String id) {
        String sql = "SELECT C.ID , C.NAME , CPP.EXPIRATION_DATE , CPP.IDENTITY_LIMIT , CPP.QUOTA_LIMIT \n" +
                "FROM COMPANY  C\n" +
                "JOIN  COMPANY_PAYMENT_PACKAGE CPP ON C.COMPANY_PAYMENT_PACKAGE = CPP.ID\n" +
                "WHERE C.ID = ?";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (rs, rowNum) -> new Company(rs.getLong("ID"),
                        rs.getString("NAME"),
                        rs.getString("EXPIRATION_DATE"),
                        rs.getLong("IDENTITY_LIMIT"),
                        rs.getLong("QUOTA_LIMIT"))
        );
    }
}
