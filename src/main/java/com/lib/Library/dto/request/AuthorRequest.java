package com.lib.Library.dto.request;

import com.lib.Library.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class AuthorRequest {

    private String email;
    private String phoneNumber;
    private Gender gender;
    private OffsetDateTime birthDate;
    private String socialSecurityNumber;
    private String description;
    private String name;
    private String address;
}
