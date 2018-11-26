package com.b7f50.ccpp.server.services;

import com.b7f50.ccpp.server.dtos.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class CompanyService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public Company save(Company company) {
        LocalDateTime localDateTime = LocalDateTime.parse(company.getExpirationDate(), formatter);

        Company old = this.get(company.getId());
        log.warn("following update will be performed: \n" +
                "old company: " + old.toString() + "\n" +
                "new company: " + company.toString());

        String sql = "UPDATE COMPANY_PAYMENT_PACKAGE SET EXPIRATION_DATE = ?, IDENTITY_LIMIT = ?, QUOTA_LIMIT = ? " +
                "WHERE ID = (SELECT COMPANY_PAYMENT_PACKAGE FROM COMPANY WHERE ID = ?)";

        jdbcTemplate.update(sql, new Object[]{
                OffsetDateTime.parse(company.getExpirationDate()),
                company.getUsersLimit(),
                company.getQuota(),
                company.getId()});

        Company updated = this.get(company.getId());

        log.warn("result company: \n" +
                "updated company: " + updated.toString());

        return updated;
    }

    public List<Company> find(String name) {
        String sql = "SELECT C.ID, C.NAME, CPP.EXPIRATION_DATE, CPP.IDENTITY_LIMIT, CPP.QUOTA_LIMIT " +
                "FROM COMPANY AS C " +
                "INNER JOIN  COMPANY_PAYMENT_PACKAGE AS CPP ON C.COMPANY_PAYMENT_PACKAGE  = CPP.ID " +
                "WHERE LOWER(C.NAME) LIKE LOWER(?) " +
                "ORDER BY C.CREATED DESC " +
                "LIMIT 10 ";

        return jdbcTemplate.query(
                sql, new Object[]{"%" + name + "%"},
                (rs, rowNum) -> new Company(rs.getLong("ID"),
                        rs.getString("NAME"),
                        formatter.format(rs.getObject("EXPIRATION_DATE", LocalDateTime.class)),
                        rs.getLong("IDENTITY_LIMIT"),
                        rs.getLong("QUOTA_LIMIT"))
        );
    }

    public Company get(Long id) {
        String sql = "SELECT C.ID , C.NAME , CPP.EXPIRATION_DATE , CPP.IDENTITY_LIMIT , CPP.QUOTA_LIMIT " +
                "FROM COMPANY AS C " +
                "INNER JOIN COMPANY_PAYMENT_PACKAGE AS CPP ON C.COMPANY_PAYMENT_PACKAGE = CPP.ID " +
                "WHERE C.ID = ?";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (rs, rowNum) -> new Company(rs.getLong("ID"),
                        rs.getString("NAME"),
                        formatter.format(rs.getObject("EXPIRATION_DATE", LocalDateTime.class)),
                        rs.getLong("IDENTITY_LIMIT"),
                        rs.getLong("QUOTA_LIMIT"))
        );
    }
}
