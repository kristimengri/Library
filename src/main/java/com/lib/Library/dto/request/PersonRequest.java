package com.lib.Library.dto.request;

import com.lib.Library.enums.Gender;

import java.time.OffsetDateTime;

public class PersonRequest {

    private String email;
    private String address;
    private String phoneNumber;
    private Gender gender;
    private OffsetDateTime birthDate;
    private String socialSecurityNumber;
    private String name;
    private String description;

}
