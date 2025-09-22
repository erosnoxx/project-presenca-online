package com.erosnoxx.presenca.core.application.commands.input.classroom;

public record GetClassroomByClassNameInputCommand(String className) {
    public static GetClassroomByClassNameInputCommand of(String className) {
        return new GetClassroomByClassNameInputCommand(className);
    }
}
