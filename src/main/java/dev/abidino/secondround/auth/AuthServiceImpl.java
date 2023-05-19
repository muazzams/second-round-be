package dev.abidino.secondround.auth;

import dev.abidino.secondround.security.JwtTokenUtil;
import dev.abidino.secondround.user.business.User;
import dev.abidino.secondround.user.business.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public record AuthServiceImpl(UserService userService, PasswordEncoder passwordEncoder) implements AuthService {

    @Override
    public TokenResource getToken(User authenticateUser) {
        User matchedUser = userService.isMatch(authenticateUser);
        return JwtTokenUtil.generateToken(matchedUser);
    }
}
