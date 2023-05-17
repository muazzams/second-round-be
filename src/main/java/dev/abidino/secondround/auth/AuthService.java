package dev.abidino.secondround.auth;

import dev.abidino.secondround.user.business.User;

public interface AuthService {
    TokenResource getToken(User authenticateUser);
}
