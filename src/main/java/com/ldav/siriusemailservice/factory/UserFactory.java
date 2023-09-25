package com.ldav.siriusemailservice.factory;

import com.ldav.siriusemailservice.domain.Role;
import com.ldav.siriusemailservice.domain.User;
import com.ldav.siriusemailservice.domain.dto.SignupForm;
import com.ldav.siriusemailservice.domain.dto.UserCommonData;
import org.springframework.stereotype.Component;

@Component
public class UserFactory implements SimpleFactory<User, UserCommonData, SignupForm>{

    @Override
    public UserCommonData getDTOFrom(User user) {
        return new UserCommonData(user.getUsername(), user.getPassword(), user.getRole());
    }

    @Override
    public User getNewEntityFrom(SignupForm form) {
        return new User(form.getUsername(), form.getPassword(), Role.USER);
    }

}
