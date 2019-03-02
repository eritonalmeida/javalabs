package app.model.security;

import java.util.List;
import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import app.model.entity.UserEntity;

public class CustomUserDetails implements UserDetails, CredentialsContainer {

    private final String name;
    private String password;
    private final String username;
    private final List<GrantedAuthority> authorities;
    private final boolean accountNonExpired = true;
    private final boolean accountNonLocked = true;
    private final boolean credentialsNonExpired = true;
    private final boolean enabled;

    public CustomUserDetails(UserEntity user) {
        
        name = user.getName();
        password = user.getPassword();
        username = user.getLogin();

        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRoles()));
        authorities = roles;

        enabled = user.getStatus().equals("A");
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void eraseCredentials() {
        password = null;
    }
}
