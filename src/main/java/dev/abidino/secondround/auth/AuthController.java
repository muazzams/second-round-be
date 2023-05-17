package dev.abidino.secondround.auth;

import dev.abidino.secondround.security.JwtTokenUtil;
import dev.abidino.secondround.security.SecurityConstant;
import dev.abidino.secondround.user.business.User;
import dev.abidino.secondround.user.web.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import static dev.abidino.secondround.auth.AuthController.API;

@RestController
@RequestMapping(API)
@CrossOrigin
record AuthController(AuthService authService) {

    public static final String API = "/api/v1/auth";

    @PostMapping(value = "/login")
    void login(@RequestBody @Valid UserDto dto, HttpServletResponse response) {
        User user = new User(dto);
        TokenResource token = authService.getToken(user);
        Cookie cookie = JwtTokenUtil.generateCookie(SecurityConstant.TOKEN_COOKIE_NAME, token.token());
        response.addCookie(cookie);
    }

    @PostMapping(value = "/logout")
    void logout(HttpServletResponse response) {
        Cookie cookie = JwtTokenUtil.generateCookie(SecurityConstant.TOKEN_COOKIE_NAME, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
