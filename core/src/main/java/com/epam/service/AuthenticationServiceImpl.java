package com.epam.service;

import com.epam.exception.AuthenticationDataException;
import com.epam.security.JwtTokenUtil;
import com.epam.security.JwtUser;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public String createToken(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
            return jwtTokenUtil.createToken(jwtUser.getUsername(), convertToStringList(jwtUser.getAuthorities()));
        } catch (AuthenticationException e) {
            throw new AuthenticationDataException("invalid.login.password");
        }
    }

    private List<String> convertToStringList(Collection<? extends GrantedAuthority> authorities) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }
}
