package dev.abidino.secondround.user.data;

import dev.abidino.secondround.user.business.Role;
import dev.abidino.secondround.user.business.User;
import jakarta.persistence.*;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    public UserEntity(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public UserEntity() {
    }

    public UserEntity(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Role getUserType() {
        return role;
    }
}
