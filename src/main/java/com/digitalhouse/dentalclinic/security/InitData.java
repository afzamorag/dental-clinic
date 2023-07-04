package com.digitalhouse.dentalclinic.security;

import com.digitalhouse.dentalclinic.entity.User;
import com.digitalhouse.dentalclinic.entity.UserRole;
import com.digitalhouse.dentalclinic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class InitData implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = "Admin123";
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        User user = new User("Administrator", "administrator", "administrator@gmail.com", encryptedPassword, UserRole.ROLER_ADMIN);
        userRepository.save(user);
    }
}
