package dev.abidino.secondround.auth;

import dev.abidino.secondround.security.CookieService;
import dev.abidino.secondround.security.SecurityConstant;
import dev.abidino.secondround.user.business.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.abidino.secondround.auth.AuthController.API;

@RestController
@RequestMapping(API)
public class AuthController {

    private final AuthService authService;
    private final CookieService cookieService;

    public static final String API = "/api/v1/auth";

    public AuthController(AuthService authService, CookieService cookieService) {
        this.authService = authService;
        this.cookieService = cookieService;
    }

    @PostMapping(value = "/login")
    public void login(@RequestBody @Valid LoginDto dto, HttpServletResponse response) {
        User user = new User(dto);
        TokenResource token = authService.getToken(user);
        Cookie cookie = cookieService.generateCookie(SecurityConstant.TOKEN_COOKIE_NAME, token.token());
        response.addCookie(cookie);
    }

    @PostMapping(value = "/logout")
    public void logout(HttpServletResponse response) {
        Cookie cookie = cookieService.generateCookie(SecurityConstant.TOKEN_COOKIE_NAME, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
