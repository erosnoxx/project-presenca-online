package com.erosnoxx.presenca.api.controllers;

import com.erosnoxx.presenca.api.schemas.request.user.CreateUserRequest;
import com.erosnoxx.presenca.api.schemas.response.common.UUIDResponse;
import com.erosnoxx.presenca.core.application.contracts.usecases.users.CreateUserUseCase;
import com.erosnoxx.presenca.infrastructure.annotations.AdminOnly;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController @RequestMapping("users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping @AdminOnly
    public ResponseEntity<UUIDResponse> createUser(
            @RequestBody @Valid CreateUserRequest request) {
        var response = UUIDResponse
                .fromOutput(createUserUseCase
                        .execute(request.toInput()));
        var location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/users/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(response);
    }
}
