package dev.abidino.secondround.user.business;

import java.util.List;

public interface UserService {
    User findByUsername(String username);

    User save(User user);

    List<User> findAll();

    boolean isMatch(User authenticateUser);
}
