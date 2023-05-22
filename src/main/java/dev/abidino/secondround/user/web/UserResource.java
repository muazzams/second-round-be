package dev.abidino.secondround.user.web;

import dev.abidino.secondround.user.business.Role;
import dev.abidino.secondround.user.business.User;

public record UserResource(Long id, String username, Role role) {
    UserResource(User user) {
        this(user.getId(), user.getUsername(), user.getRole());
    }
}
