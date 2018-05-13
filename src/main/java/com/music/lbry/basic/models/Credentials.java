package com.music.lbry.basic.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Credentials {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
