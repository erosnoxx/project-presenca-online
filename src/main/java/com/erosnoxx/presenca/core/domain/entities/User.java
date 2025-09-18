package com.erosnoxx.presenca.core.domain.entities;

import com.erosnoxx.presenca.core.domain.entities.common.DomainEntity;
import com.erosnoxx.presenca.core.domain.enums.Role;
import com.erosnoxx.presenca.core.domain.value_objects.Password;
import com.erosnoxx.presenca.core.domain.value_objects.Username;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class User extends DomainEntity<UUID> {
    private Username username;
    private Password password;
    private Role role;

    public User(UUID id) {
        setId(id);
    }
}
