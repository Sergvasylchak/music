package com.music.lbry.basic.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.lbry.basic.models.LibraryUserDetails;
import com.music.lbry.models.entities.LibraryUser;
import com.music.lbry.models.enums.Role;
import com.music.lbry.utils.Constants;
import com.music.lbry.utils.SystemConfig;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            val user = new ObjectMapper()
                    .readValue(request.getInputStream(), LibraryUser.class);
            val userDetails = LibraryUserDetails.of(user);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(),
                            userDetails.getPassword(),
                            userDetails.getAuthorities())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
        val claims = Jwts.claims();
        claims.put(Constants.ROLE_KEY, auth.getAuthorities().stream().findAny()
                .map(GrantedAuthority::getAuthority).orElse(Role.USER.getRole()));
        claims.setSubject(((LibraryUserDetails) auth.getPrincipal()).getUsername());
        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + SystemConfig.getTokenExpirationTime()))
                .signWith(SignatureAlgorithm.HS512, SystemConfig.getKey())
                .compact();
        response.addHeader(Constants.AUTH_HEADER, Constants.BEARER + token);
    }
}