package com.lib.Library.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(indexes = {
        @Index(name = "index_lender_email", columnList = "email"),
        @Index(name = "index_lender_social_security_number", columnList = "socialSecurityNumber"),
        @Index(name = "index_lender_name", columnList = "name")
})
public class Lender extends Person {

    @OneToMany(mappedBy = "lender")
    private List<LenderToBookInstance> bookInstances;

    private boolean blocked;
}
