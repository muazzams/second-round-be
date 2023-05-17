package dev.abidino.secondround.user.web;

import dev.abidino.secondround.user.business.Role;
import dev.abidino.secondround.user.business.User;

public record UserResource(String username, Role role) {
    UserResource(User user) {
        this(user.getUsername(), user.getRole());
    }
}
