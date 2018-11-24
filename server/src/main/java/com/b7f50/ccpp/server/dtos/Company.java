package com.b7f50.ccpp.server.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {
    private long id;
    private String name;
    private String expirationDate;
    private long usersLimit;
    private long quota;
}
