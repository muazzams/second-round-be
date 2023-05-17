package dev.abidino.secondround.user.data;

import dev.abidino.secondround.exception.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.abidino.secondround.exception.ErrorMessageType.USERNAME_NOT_FOUND;

@Service
public record DataServiceImpl(UserRepository userRepository) implements DataService {
    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(USERNAME_NOT_FOUND.getMessage()));
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean isExistUserName(String username) {
        return userRepository.existsByUsername(username);
    }
}
