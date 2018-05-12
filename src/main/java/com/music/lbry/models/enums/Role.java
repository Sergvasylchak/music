package com.music.lbry.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("ROLE_USER", "LibraryUser"), ADMIN("ROLE_ADMIN", "Admin");

    private String role;
    private String name;
}
