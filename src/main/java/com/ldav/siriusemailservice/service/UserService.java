package com.ldav.siriusemailservice.service;

import com.ldav.siriusemailservice.domain.dto.SignupForm;
import com.ldav.siriusemailservice.domain.dto.UserCommonData;
import com.ldav.siriusemailservice.factory.UserFactory;
import com.ldav.siriusemailservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserFactory userFactory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(userFactory::getDTOFrom)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found!"));
    }

    public UserCommonData create(SignupForm form){
        var user = userFactory.getNewEntityFrom(form);
        userRepository.save(user);
        return userFactory.getDTOFrom(user);
    }
}
