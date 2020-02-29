package com.epam.lab.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Role {

    @Column(name = "role_name", nullable = false, length = 30)
    private String roleName;
}
