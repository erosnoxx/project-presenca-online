package com.erosnoxx.presenca.api.controllers;

import com.erosnoxx.presenca.api.schemas.request.student.CreateStudentRequest;
import com.erosnoxx.presenca.api.schemas.request.student.RecordAttendanceRequest;
import com.erosnoxx.presenca.api.schemas.response.common.UUIDResponse;
import com.erosnoxx.presenca.core.application.contracts.usecases.student.CreateStudentUseCase;
import com.erosnoxx.presenca.core.application.contracts.usecases.student.RecordAttendanceUseCase;
import com.erosnoxx.presenca.infrastructure.annotations.AdminOnly;
import com.erosnoxx.presenca.infrastructure.annotations.UserOnly;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RestController @RequestMapping("students")
public class StudentController {
    private final CreateStudentUseCase createStudentUseCase;
    private final RecordAttendanceUseCase recordAttendanceUseCase;

    public StudentController(
            CreateStudentUseCase createStudentUseCase,
            RecordAttendanceUseCase recordAttendanceUseCase) {
        this.createStudentUseCase = createStudentUseCase;
        this.recordAttendanceUseCase = recordAttendanceUseCase;
    }

    @PostMapping @AdminOnly
    public ResponseEntity<UUIDResponse> createStudent(
            @RequestBody @Valid CreateStudentRequest request) {
        var response = createStudentUseCase.execute(request.toInput());
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(
                UUIDResponse.fromOutput(response));
    }

    @PostMapping("/{id}/attendance") @UserOnly
    public ResponseEntity<UUIDResponse> recordAttendance(
            @PathVariable UUID id, @RequestBody @Valid RecordAttendanceRequest request) {
        return ResponseEntity.ok(
                UUIDResponse.fromOutput(
                        recordAttendanceUseCase.execute(request.toInput(id))));
    }
}
