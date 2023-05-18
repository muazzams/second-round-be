package dev.abidino.secondround.user.web;

import dev.abidino.secondround.security.ApiJWTAuthorizationFilter;
import dev.abidino.secondround.user.business.User;
import dev.abidino.secondround.user.business.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static dev.abidino.secondround.user.web.UserController.API;

@RestController
@RequestMapping(API)
record UserController(UserService userService) {
    public static final String API = "api/v1/user";

    @GetMapping("/all")
    List<UserResource> all() {
        return userService.findAll().stream().map(UserResource::new).toList();
    }

    @GetMapping("/check")
    void check() {
        System.out.println(ApiJWTAuthorizationFilter.getAuthenticatedUserName());
    }


    @PostMapping("/register")
    UserResource register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        User user = userService.save(new User(userRegisterDto));
        return new UserResource(user);
    }
}

