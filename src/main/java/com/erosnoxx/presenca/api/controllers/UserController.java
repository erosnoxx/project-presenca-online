package com.erosnoxx.presenca.api.controllers;

import com.erosnoxx.presenca.api.schemas.request.user.CreateUserRequest;
import com.erosnoxx.presenca.api.schemas.request.user.UpdateUserRequest;
import com.erosnoxx.presenca.api.schemas.response.common.UUIDResponse;
import com.erosnoxx.presenca.core.application.commands.input.user.*;
import com.erosnoxx.presenca.core.application.contracts.usecases.users.*;
import com.erosnoxx.presenca.core.application.dto.user.UserDto;
import com.erosnoxx.presenca.infrastructure.annotations.AdminOnly;
import com.erosnoxx.presenca.infrastructure.annotations.UserOnly;
import com.erosnoxx.presenca.infrastructure.persistence.entities.UserEntity;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.util.UUID;

@RestController @RequestMapping("users")
public class UserController {
    private final CreateUserUseCase createUserUseCase;
    private final GetUsersUseCase getUsersUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetUserByUsernameUseCase getUserByUsernameUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    public UserController(
            CreateUserUseCase createUserUseCase,
            GetUsersUseCase getUsersUseCase,
            GetUserByIdUseCase getUserByIdUseCase,
            GetUserByUsernameUseCase getUserByUsernameUseCase,
            UpdateUserUseCase updateUserUseCase,
            DeleteUserUseCase deleteUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getUsersUseCase = getUsersUseCase;
        this.getUserByIdUseCase = getUserByIdUseCase;
        this.getUserByUsernameUseCase = getUserByUsernameUseCase;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUseCase = deleteUserUseCase;
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

    @GetMapping @UserOnly
    public ResponseEntity<Page<UserDto>> getUsers(Pageable pageable) {
        return ResponseEntity.ok(
                getUsersUseCase.execute(GetUsersInputCommand.of(pageable))
                        .users()
        );
    }

    @GetMapping("/{id}") @UserOnly
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                getUserByIdUseCase.execute(GetUserByIdInputCommand.of(id))
                        .user()
        );
    }

    @GetMapping("/search") @UserOnly
    public ResponseEntity<UserDto> getUserByUsername(@RequestParam String username) {
        return ResponseEntity.ok(
                getUserByUsernameUseCase.execute(GetUserByUsernameInputCommand.of(username))
                        .user()
        );
    }

    @PatchMapping("/{id}") @UserOnly
    public ResponseEntity<UUIDResponse> updateUser(
            @PathVariable UUID id,
            @RequestBody UpdateUserRequest request) {
        return ResponseEntity.ok(UUIDResponse.fromOutput(
                updateUserUseCase.execute(UpdateUserInputCommand.of(
                        id, request.username(), request.password()
                ))
        ));
    }

    @DeleteMapping("/{id}") @AdminOnly
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        deleteUserUseCase.execute(DeleteUserInputCommand.of(id));
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/me") @UserOnly
    public ResponseEntity<UserDto> getCurrentUser(@AuthenticationPrincipal UserEntity user) {
        return ResponseEntity.ok(
                new UserDto(
                        user.getId(),
                        user.getUsername(),
                        user.getRole(),
                        user.getCreatedAt(),
                        user.getUpdatedAt())
        );
    }
}
