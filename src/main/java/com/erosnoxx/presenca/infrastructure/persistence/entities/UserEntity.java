package com.erosnoxx.presenca.infrastructure.persistence.entities;

import com.erosnoxx.presenca.core.domain.enums.Role;
import com.erosnoxx.presenca.infrastructure.persistence.entities.common.JpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity @Table(name = "users")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserEntity extends JpaEntity<UUID> implements UserDetails {
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch(role) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN)"),
                    new SimpleGrantedAuthority("ROLE_USER"));
            case USER -> List.of(
                    new SimpleGrantedAuthority("ROLE_USER"));
        };
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
