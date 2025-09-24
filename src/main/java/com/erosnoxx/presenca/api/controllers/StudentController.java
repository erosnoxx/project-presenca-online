package com.erosnoxx.presenca.api.controllers;

import com.erosnoxx.presenca.api.schemas.request.student.CreateStudentRequest;
import com.erosnoxx.presenca.api.schemas.request.student.RecordAttendanceRequest;
import com.erosnoxx.presenca.api.schemas.request.student.UpdateStudentRequest;
import com.erosnoxx.presenca.api.schemas.response.common.UUIDResponse;
import com.erosnoxx.presenca.core.application.commands.input.student.CalculateAttendancePercentageInputCommand;
import com.erosnoxx.presenca.core.application.contracts.usecases.student.CalculateAttendancePercentageUseCase;
import com.erosnoxx.presenca.core.application.contracts.usecases.student.CreateStudentUseCase;
import com.erosnoxx.presenca.core.application.contracts.usecases.student.RecordAttendanceUseCase;
import com.erosnoxx.presenca.core.application.contracts.usecases.student.UpdateStudentUseCase;
import com.erosnoxx.presenca.core.application.dto.entities.StudentDto;
import com.erosnoxx.presenca.infrastructure.annotations.AdminOnly;
import com.erosnoxx.presenca.infrastructure.annotations.UserOnly;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;
import java.util.UUID;

@RestController @RequestMapping("students")
public class StudentController {
    private final CreateStudentUseCase createStudentUseCase;
    private final RecordAttendanceUseCase recordAttendanceUseCase;
    private final CalculateAttendancePercentageUseCase calculateAttendancePercentageUseCase;
    private final UpdateStudentUseCase updateStudentUseCase;

    public StudentController(
            CreateStudentUseCase createStudentUseCase,
            RecordAttendanceUseCase recordAttendanceUseCase,
            CalculateAttendancePercentageUseCase calculateAttendancePercentageUseCase,
            UpdateStudentUseCase updateStudentUseCase) {
        this.createStudentUseCase = createStudentUseCase;
        this.recordAttendanceUseCase = recordAttendanceUseCase;
        this.calculateAttendancePercentageUseCase = calculateAttendancePercentageUseCase;
        this.updateStudentUseCase = updateStudentUseCase;
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

    @GetMapping("/{id}/attendance") @AdminOnly
    public ResponseEntity<StudentDto> getAttendanceById(
            @PathVariable UUID id,
            @RequestParam(required = false) LocalDate start,
            @RequestParam(required = false) LocalDate end) {
        return ResponseEntity.ok(
                calculateAttendancePercentageUseCase.execute(
                        CalculateAttendancePercentageInputCommand.of(id, start, end)));
    }

    @PatchMapping("/{id}") @AdminOnly
    public ResponseEntity<UUIDResponse> updateStudent(
            @PathVariable UUID id,
            @RequestBody UpdateStudentRequest request) {
        return ResponseEntity.ok(UUIDResponse.fromOutput(
                updateStudentUseCase.execute(request.toInput(id))));
    }
}
