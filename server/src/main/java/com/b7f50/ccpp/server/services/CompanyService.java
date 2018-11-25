package com.b7f50.ccpp.server.services;

import com.b7f50.ccpp.server.dtos.Company;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class CompanyService {

    @Autowired
    JdbcTemplate jdbcTemplate;
    //2049-02-19T23:00:00.000Z
    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public Company save(Company company) {
        log.warn(company.getExpirationDate());
        LocalDateTime localDateTime = LocalDateTime.parse(company.getExpirationDate(), format);
        log.warn(localDateTime.toString());

        Company old = this.get(String.valueOf(company.getId()));
        log.warn("following update will be performed: \n" +
                "old company: " + old.toString() + "\n" +
                "new company: " + company.toString());

        String sql = "UPDATE COMPANY_PAYMENT_PACKAGE CPP SET CPP.EXPIRATION_DATE = ?, CPP.IDENTITY_LIMIT = ?, CPP.QUOTA_LIMIT = ? " +
                "WHERE CPP.ID = (SELECT COMPANY_PAYMENT_PACKAGE FROM COMPANY WHERE ID = ?)";

        jdbcTemplate.update(sql, new Object[]{company.getExpirationDate(), company.getUsersLimit(), company.getQuota(), company.getId()});

        Company updated = this.get(String.valueOf(company.getId()));

        log.warn("result company: \n" +
                "updated company: " + updated.toString());

        return updated;
    }

    public List<Company> find(String name) {

        log.warn("queried with name: " + name);

        String sql = "SELECT C.ID, C.NAME, CPP.EXPIRATION_DATE, CPP.IDENTITY_LIMIT, CPP.QUOTA_LIMIT " +
                "FROM COMPANY AS C " +
                "INNER JOIN  COMPANY_PAYMENT_PACKAGE AS CPP ON C.COMPANY_PAYMENT_PACKAGE  = CPP.ID " +
                "WHERE C.NAME LIKE ? " +
                "LIMIT 10";

        return jdbcTemplate.query(
                sql, new Object[]{name + "%"},
                (rs, rowNum) -> new Company(rs.getLong("ID"),
                        rs.getString("NAME"),
                        format.format(rs.getObject("EXPIRATION_DATE", LocalDateTime.class)),
                        rs.getLong("IDENTITY_LIMIT"),
                        rs.getLong("QUOTA_LIMIT"))
        );
    }



    public Company get(String id) {
        String sql = "SELECT C.ID , C.NAME , CPP.EXPIRATION_DATE , CPP.IDENTITY_LIMIT , CPP.QUOTA_LIMIT " +
                "FROM COMPANY AS C " +
                "INNER JOIN COMPANY_PAYMENT_PACKAGE AS CPP ON C.COMPANY_PAYMENT_PACKAGE = CPP.ID " +
                "WHERE C.ID = ?";

        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (rs, rowNum) -> new Company(rs.getLong("ID"),
                        rs.getString("NAME"),
                        format.format(rs.getObject("EXPIRATION_DATE", LocalDateTime.class)),
                        rs.getLong("IDENTITY_LIMIT"),
                        rs.getLong("QUOTA_LIMIT"))
        );
    }
}
