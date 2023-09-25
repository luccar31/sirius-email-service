package com.ldav.siriusemailservice.config;

import com.ldav.siriusemailservice.domain.Role;
import com.ldav.siriusemailservice.domain.User;
import com.ldav.siriusemailservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        var admin = new User();
        admin.setUsername("admin1@admin.ldav");
        admin.setPassword(encoder.encode("admin"));
        admin.setRole(Role.ADMIN);
        var user = new User();
        user.setUsername("lucas.david.car99@gmail.com");
        user.setPassword(encoder.encode("user"));
        user.setRole(Role.USER);
        userRepository.save(admin);
        userRepository.save(user);
    }
}
