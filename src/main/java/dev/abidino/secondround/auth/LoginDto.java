package dev.abidino.secondround.auth;

import jakarta.validation.constraints.NotNull;

public record LoginDto(@NotNull(message = "Username can not be null") String username,
                       @NotNull(message = "Password can not be null") String password) {
}
