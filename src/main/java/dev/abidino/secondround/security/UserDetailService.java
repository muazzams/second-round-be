package dev.abidino.secondround.security;

import dev.abidino.secondround.exception.ErrorMessageType;
import dev.abidino.secondround.user.business.User;
import dev.abidino.secondround.user.business.UserService;
import dev.abidino.secondround.user.data.UserEntity;
import dev.abidino.secondround.user.data.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    private final UserService userService;

    public UserDetailService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByUsername(username);
        return new MyUserDetails(user);
    }
}

