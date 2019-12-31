package com.iosis.backofficejeuxvideo.service;

import com.iosis.backofficejeuxvideo.repository.UserRepository;
import com.iosis.backofficejeuxvideo.model.security.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
//        User user = userRepository.findUserByUsernameAndPassword(username, password)
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return user;
    }

    public UserDetails loadUserByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {
        Objects.requireNonNull(username);
        User user = userRepository.findUserByUsernameAndPassword(username, password)
                .orElseThrow(() -> new UsernameNotFoundException("User/Password not found"));
        return user;
    }

}