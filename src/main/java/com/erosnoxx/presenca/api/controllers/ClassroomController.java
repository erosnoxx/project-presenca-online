package com.erosnoxx.presenca.api.controllers;

import com.erosnoxx.presenca.api.schemas.request.classroom.CreateClassroomRequest;
import com.erosnoxx.presenca.api.schemas.response.common.UUIDResponse;
import com.erosnoxx.presenca.core.application.commands.input.classroom.GetClassroomByClassNameInputCommand;
import com.erosnoxx.presenca.core.application.commands.input.classroom.GetClassroomByIdInputCommand;
import com.erosnoxx.presenca.core.application.commands.input.classroom.GetClassroomsInputCommand;
import com.erosnoxx.presenca.core.application.contracts.usecases.classroom.CreateClassroomUseCase;
import com.erosnoxx.presenca.core.application.contracts.usecases.classroom.GetClassroomByClassNameUseCase;
import com.erosnoxx.presenca.core.application.contracts.usecases.classroom.GetClassroomByIdUseCase;
import com.erosnoxx.presenca.core.application.contracts.usecases.classroom.GetClassroomsUseCase;
import com.erosnoxx.presenca.core.application.dto.entities.ClassroomDto;
import com.erosnoxx.presenca.infrastructure.annotations.AdminOnly;
import com.erosnoxx.presenca.infrastructure.annotations.UserOnly;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController @RequestMapping("classrooms")
public class ClassroomController {
    private final CreateClassroomUseCase createClassroomUseCase;
    private final GetClassroomsUseCase getClassroomsUseCase;
    private final GetClassroomByIdUseCase getClassroomByIdUseCase;
    private final GetClassroomByClassNameUseCase getClassroomByClassNameUseCase;

    public ClassroomController(
            CreateClassroomUseCase createClassroomUseCase,
            GetClassroomsUseCase getClassroomsUseCase,
            GetClassroomByIdUseCase getClassroomByIdUseCase,
            GetClassroomByClassNameUseCase getClassroomByClassNameUseCase) {
        this.createClassroomUseCase = createClassroomUseCase;
        this.getClassroomsUseCase = getClassroomsUseCase;
        this.getClassroomByIdUseCase = getClassroomByIdUseCase;
        this.getClassroomByClassNameUseCase = getClassroomByClassNameUseCase;
    }

    @PostMapping @AdminOnly
    public ResponseEntity<UUIDResponse> createClassroom(
            @RequestBody @Valid CreateClassroomRequest request) {
        var response = createClassroomUseCase.execute(request.toInput());
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        return ResponseEntity.created(location).body(UUIDResponse.fromOutput(response));
    }

    @GetMapping @UserOnly
    public ResponseEntity<Page<ClassroomDto>> getClassrooms(Pageable pageable) {
        return ResponseEntity.ok(
                getClassroomsUseCase.execute(
                        GetClassroomsInputCommand.of(pageable))
                        .classrooms());
    }

    @GetMapping("/id") @UserOnly
    public ResponseEntity<ClassroomDto> getClassroomById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                getClassroomByIdUseCase.execute(GetClassroomByIdInputCommand
                                .of(id))
                        .classroom()
        );
    }

    @GetMapping("/search") @UserOnly
    public ResponseEntity<ClassroomDto> getClassroomByClassName(@RequestParam String className) {
        return ResponseEntity.ok(
                getClassroomByClassNameUseCase.execute(GetClassroomByClassNameInputCommand
                                .of(className))
                        .classroom()
        );
    }
}
