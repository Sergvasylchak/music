package com.music.lbry.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.music.lbry.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "lbry_users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryUser {

    @Id
    private Long id;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private String firstName;

    private String lastName;

    @JsonIgnore
    private Boolean locked;
}
