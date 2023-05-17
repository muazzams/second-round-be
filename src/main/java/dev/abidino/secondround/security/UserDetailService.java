package dev.abidino.secondround.security;


import dev.abidino.secondround.user.business.Role;
import dev.abidino.secondround.user.business.User;
import dev.abidino.secondround.user.business.UserServiceImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


@Service
public class UserDetailService implements UserDetailsService {

    private final UserServiceImpl userService;

    public UserDetailService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = userService.findByUsername(username);
        return buildUserForAuthentication(user);
    }

    private UserDetails buildUserForAuthentication(User user) {
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user.getRole()));
    }


    public Collection<GrantedAuthority> getAuthorities(Role role) {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
}

