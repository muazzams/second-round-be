package dev.abidino.secondround.user.business;

import dev.abidino.secondround.exception.BadRequestException;
import dev.abidino.secondround.exception.ErrorMessageType;
import dev.abidino.secondround.user.data.UserDataService;
import dev.abidino.secondround.user.data.UserEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public
record UserServiceImpl(UserDataService dataService, PasswordEncoder passwordEncoder) implements UserService {

    @Override
    public User findByUsername(String username) {
        UserEntity userEntity = dataService.findByUsername(username);
        return new User(userEntity);
    }

    @Override
    public User save(User user) {
        boolean existUserName = dataService.isExistUserName(user.getUsername());
        if (existUserName) {
            throw new BadRequestException(ErrorMessageType.USERNAME_ALREADY_EXIST.getMessage());
        }
        user.setPassword(passwordEncoder);
        UserEntity savedUserEntity = dataService.save(new UserEntity(user));
        return new User(savedUserEntity);
    }

    @Override
    public List<User> findAll() {
        return dataService.findAll().stream().map(User::new).toList();
    }

    @Override
    public User isMatch(User authenticateUser) {
        User user = findByUsername(authenticateUser.getUsername());
        boolean isMatch = user.matchPassword(passwordEncoder, authenticateUser.getPassword());
        if (isMatch) {
            return user;
        }
        throw new BadRequestException(ErrorMessageType.USERNAME_AND_PASSWORD_NOT_MATCH.getMessage());
    }
}
