package com.digitalhouse.dentalclinic.service;

import com.digitalhouse.dentalclinic.entity.User;
import com.digitalhouse.dentalclinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> selected = repository.findByEmail(username);
        if (selected.isPresent()) {
            return selected.get();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado.");
        }
    }
}
