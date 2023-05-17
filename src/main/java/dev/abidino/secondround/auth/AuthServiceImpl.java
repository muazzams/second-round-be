package dev.abidino.secondround.auth;

import dev.abidino.secondround.exception.BadRequestException;
import dev.abidino.secondround.exception.ErrorMessageType;
import dev.abidino.secondround.security.JwtTokenUtil;
import dev.abidino.secondround.user.business.User;
import dev.abidino.secondround.user.business.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder) implements AuthService {

    @Override
    public TokenResource getToken(User authenticateUser) {
        boolean isMatch = userService.isMatch(authenticateUser);
        if (isMatch) {
            return JwtTokenUtil.generateToken(authenticateUser);
        } else {
            throw new BadRequestException(ErrorMessageType.USERNAME_AND_PASSWORD_NOT_MATCH.getMessage());
        }
    }
}
