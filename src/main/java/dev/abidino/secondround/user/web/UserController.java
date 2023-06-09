package dev.abidino.secondround.user.web;

import dev.abidino.secondround.user.business.User;
import dev.abidino.secondround.user.business.UserService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static dev.abidino.secondround.user.web.UserController.API;

@RestController
@RequestMapping(API)
public class UserController {

    private final UserService userService;
    public static final String API = "api/v1/user";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserResource> all() {
        return userService.findAll().stream().map(UserResource::new).toList();
    }

    @GetMapping("/check")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void check() {
    }


    @PostMapping("/register")
    @PreAuthorize("hasAuthority('ADMIN')")
    public UserResource register(@RequestBody @Valid UserRegisterDto userRegisterDto) {
        User user = userService.save(new User(userRegisterDto));
        return new UserResource(user);
    }
}

