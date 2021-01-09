package ru.veryprosto.restVote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.veryprosto.restVote.model.Role;
import ru.veryprosto.restVote.model.User;


@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityManager securityManager;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.getByName(username);

        //запоминаем юзера для текущей сессии
        securityManager.setAuthUserId(user);

        UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            builder.disabled(false);
            builder.password(user.getPassword());
            String[] authorities = user.getRoles().stream().map(Role::name).toArray(String[]::new);
            builder.authorities(authorities);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }
}
