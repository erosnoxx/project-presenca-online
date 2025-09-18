package com.erosnoxx.presenca.infrastructure.seeder;

import com.erosnoxx.presenca.core.domain.enums.Role;
import com.erosnoxx.presenca.infrastructure.persistence.entities.UserEntity;
import com.erosnoxx.presenca.infrastructure.persistence.repositories.UserJpaRepository;
import com.erosnoxx.presenca.infrastructure.seeder.common.Seeder;
import com.erosnoxx.presenca.infrastructure.seeder.dto.AdminProps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component @Slf4j
public class UserSeeder extends Seeder<UserEntity, UUID, UserJpaRepository> {

    @Value("${app.seeder.file-path}")
    private String seederFile;

    private final PasswordEncoder passwordEncoder;

    protected UserSeeder(
            UserJpaRepository repository,
            ResourceLoader resourceLoader,
            PasswordEncoder passwordEncoder) {
        super(repository, resourceLoader);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void seed() {
        loadSeederFile(seederFile);

        var admin = getAsObject("admin", AdminProps.class);

        createAdmin(admin);
    }

    private void createAdmin(AdminProps props) {
        var admin = new UserEntity();
        admin.setUsername(props.username());
        admin.setPassword(passwordEncoder.encode(props.password()));
        admin.setRole(Role.ADMIN);

        var saved = repository.save(admin);

        log.warn("⚡ Admin user created: {} / {}",
                saved.getId(),
                saved.getUsername());
    }
}
