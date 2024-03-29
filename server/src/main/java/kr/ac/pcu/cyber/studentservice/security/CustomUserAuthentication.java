package kr.ac.pcu.cyber.studentservice.security;

import kr.ac.pcu.cyber.studentservice.common.Role;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class CustomUserAuthentication extends AbstractAuthenticationToken {

    private final String userId;

    public CustomUserAuthentication(String userId, List<Role> roles) {
        super(authorities(roles));
        this.userId = userId;
    }

    private static List<? extends GrantedAuthority> authorities(List<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleType().toString()))
                .collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }
}
