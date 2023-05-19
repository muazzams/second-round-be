package dev.abidino.secondround.user.data;

import java.util.List;

public interface UserDataService {
    UserEntity findByUsername(String username);

    UserEntity save(UserEntity user);

    List<UserEntity> findAll();

    boolean isExistUserName(String username);
}
