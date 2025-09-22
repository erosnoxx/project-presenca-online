package com.erosnoxx.presenca.core.application.commands.input.classroom;

import java.util.Optional;
import java.util.UUID;

public record UpdateClassroomInputCommand(
        UUID id,
        Optional<String> className) {
}
