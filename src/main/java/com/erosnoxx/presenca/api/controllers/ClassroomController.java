package com.erosnoxx.presenca.api.controllers;

import com.erosnoxx.presenca.api.schemas.request.classroom.CreateClassroomRequest;
import com.erosnoxx.presenca.api.schemas.response.common.UUIDResponse;
import com.erosnoxx.presenca.core.application.contracts.usecases.classroom.CreateClassroomUseCase;
import com.erosnoxx.presenca.infrastructure.annotations.AdminOnly;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @RequestMapping("classrooms")
public class ClassroomController {
    private final CreateClassroomUseCase createClassroomUseCase;

    public ClassroomController(CreateClassroomUseCase createClassroomUseCase) {
        this.createClassroomUseCase = createClassroomUseCase;
    }

    @PostMapping @AdminOnly
    private ResponseEntity<UUIDResponse> createClassroom(
            @RequestBody @Valid CreateClassroomRequest request) {
        return ResponseEntity.ok(UUIDResponse
                .fromOutput(
                        createClassroomUseCase.execute(
                                request.toInput())));
    }
}
