package com.ldav.siriusemailservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User extends BaseEntity {

    @Column(unique = true)
    private String username;
    private String password;
    @Enumerated
    private Role role;

}
