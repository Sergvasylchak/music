package com.music.lbry.basic.models;

import com.music.lbry.models.entities.LibraryUser;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collections;
import java.util.Set;

@Data
@Builder
public class LibraryUserDetails implements UserDetails {

    private String username;
    private String password;
    private Boolean locked;
    private Set<GrantedAuthority> authorities;

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static LibraryUserDetails of(LibraryUser user) {
        return LibraryUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .locked(user.getLocked())
                .authorities(Collections.singleton(new SimpleGrantedAuthority(user.getRole().getRole())))
                .build();
    }
}
