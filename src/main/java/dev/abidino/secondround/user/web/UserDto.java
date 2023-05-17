package dev.abidino.secondround.user.web;

import dev.abidino.secondround.user.business.Role;
import jakarta.validation.constraints.NotNull;

public record UserDto(@NotNull(message = "Username can not be null") String username,
                      @NotNull(message = "Password can not be null") String password,
                      Role role) {
}
