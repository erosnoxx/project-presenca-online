package com.erosnoxx.presenca.core.application.commands.input.user;


import org.springframework.data.domain.Pageable;

public record GetUsersInputCommand(Pageable pageable) {
}
