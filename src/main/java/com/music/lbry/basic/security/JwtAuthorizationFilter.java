package com.music.lbry.basic.security;

import com.music.lbry.utils.Constants;
import com.music.lbry.utils.SystemConfig;
import io.jsonwebtoken.Jwts;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Objects;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authManager) {
        super(authManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        authenticate(request);
        chain.doFilter(request, response);
    }

    private void authenticate(HttpServletRequest request) {
        String token = request.getHeader(Constants.AUTH_HEADER);
        if (Objects.isNull(token) || token.isEmpty()) {
            return;
        }
        val claims = Jwts.parser()
                .setSigningKey(SystemConfig.getKey())
                .parseClaimsJws(token.replace(Constants.BEARER, Constants.BLANK))
                .getBody();
        val user = claims.getSubject();
        val authorities = new SimpleGrantedAuthority(claims.get(Constants.ROLE_KEY).toString());
        val authentication = new UsernamePasswordAuthenticationToken(user, null, Collections.singletonList(authorities));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
