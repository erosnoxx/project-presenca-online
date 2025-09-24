package com.erosnoxx.presenca.core.application.usecases.student;

import com.erosnoxx.presenca.core.application.commands.input.classroom.UpdateClassroomInputCommand;
import com.erosnoxx.presenca.core.application.commands.input.student.UpdateStudentInputCommand;
import com.erosnoxx.presenca.core.application.commands.output.common.UUIDOutputCommand;
import com.erosnoxx.presenca.core.application.contracts.repositories.ClassroomRepository;
import com.erosnoxx.presenca.core.application.contracts.repositories.StudentRepository;
import com.erosnoxx.presenca.core.application.contracts.usecases.student.UpdateStudentUseCase;
import com.erosnoxx.presenca.core.domain.entities.Classroom;
import com.erosnoxx.presenca.core.domain.entities.Student;
import com.erosnoxx.presenca.core.domain.exceptions.entities.classroom.ClassroomNotFoundException;
import com.erosnoxx.presenca.core.domain.exceptions.entities.student.StudentAlreadyExistsInClassRoomException;
import com.erosnoxx.presenca.core.domain.exceptions.entities.student.StudentNotFoundException;
import com.erosnoxx.presenca.core.domain.value_objects.Name;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateStudentUseCaseImpl implements UpdateStudentUseCase {
    private final StudentRepository repository;
    private final ClassroomRepository classroomRepository;

    public UpdateStudentUseCaseImpl(
            StudentRepository repository,
            ClassroomRepository classroomRepository) {
        this.repository = repository;
        this.classroomRepository = classroomRepository;
    }

    @Override
    public UUIDOutputCommand execute(UpdateStudentInputCommand input) {
        var student = getStudentOrThrow(input.id());

        updateNameIfPresent(student, input);
        updateRegistrationNumberIfPresent(student, input);
        updateClassroomIfPresent(student, input);

        persistIfChanged(student, input);

        return UUIDOutputCommand.of(student);
    }

    private Student getStudentOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(
                        "user not found with id: " + id));
    }

    private void updateNameIfPresent(Student student, UpdateStudentInputCommand input) {
        input.name().ifPresent(n -> {
            student.setName(Name.of(n));
        });
    }

    private void updateRegistrationNumberIfPresent(
            Student student, UpdateStudentInputCommand input) {
        input.registrationNumber().ifPresent(rn -> {
            ensureRegistrationNumberIsUnique(rn);
            student.setRegistrationNumber(rn);
        });
    }

    private void updateClassroomIfPresent(Student student, UpdateStudentInputCommand input) {
        input.classroomId().ifPresent(cn -> {
            student.transferTo(getClassroomOrThrow(cn));
        });
    }

    private void ensureRegistrationNumberIsUnique(String registrationNumber) {
        if (repository.existsByRegistrationNumber(registrationNumber))
            throw new StudentAlreadyExistsInClassRoomException(
                    "registration number already in use: " + registrationNumber);
    }

    private Classroom getClassroomOrThrow(UUID classroomId) {
        return classroomRepository.findById(classroomId)
                .orElseThrow(() -> new ClassroomNotFoundException(
                        "classroom not found with id: " + classroomId));
    }

    private void persistIfChanged(Student student, UpdateStudentInputCommand input) {
        if (input.name().isPresent()
            || input.registrationNumber().isPresent()
            || input.classroomId().isPresent()) {
            repository.save(student);
        }
    }
}
