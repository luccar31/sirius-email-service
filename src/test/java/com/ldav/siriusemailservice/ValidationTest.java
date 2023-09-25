package com.ldav.siriusemailservice;

import com.ldav.siriusemailservice.domain.dto.SignupForm;
import jakarta.validation.*;
import org.junit.jupiter.api.*;

public class ValidationTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void beforeAll(){
        Configuration<?> configuration = Validation
                .byDefaultProvider()
                .configure();

        factory = configuration.buildValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void afterAll(){
        factory.close();
    }

    @Test
    public void testRegistrationFormValidationFailsWithInvalidUsernameAndPassword(){
        var registrationForm = new SignupForm();
        registrationForm.setUsername("asd");
        registrationForm.setPassword("123");

        var violations = validator.validate(registrationForm);

        var violationsList = violations.stream().map(ConstraintViolation::getMessage).toList();

        Assertions.assertEquals(2, violations.size());
        Assertions.assertTrue(violationsList.contains("Username must be an email address"));
        Assertions.assertTrue(violationsList.contains("Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character, and be at least 8 characters long"));
    }

    @Test
    public void testRegistrationFormValidationPassesWithValidUsernameAndPassword(){
        var registrationForm = new SignupForm();
        registrationForm.setUsername("example@example.com");
        registrationForm.setPassword("$3cr3tPa55w0rd!");

        var violations = validator.validate(registrationForm);

        Assertions.assertEquals(0, violations.size());
    }

}
