package dev.abidino.secondround.user.business;


import dev.abidino.secondround.auth.LoginDto;
import dev.abidino.secondround.user.data.UserEntity;
import dev.abidino.secondround.user.web.UserRegisterDto;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

public class User {
    private Long id;

    private String username;

    private String password;

    private Role role;

    public User(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User() {

    }

    public User(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.role = userEntity.getRole();

    }

    public User(LoginDto loginDto) {
        this.username = loginDto.username();
        this.password = loginDto.password();
    }

    public User(UserRegisterDto userRegisterDto) {
        this.username = userRegisterDto.username();
        this.password = userRegisterDto.password();
        this.role = userRegisterDto.role();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public boolean matchPassword(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.matches(password, this.password);
    }

    public Role getRole() {
        return role;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(password, user.password) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role);
    }
}
