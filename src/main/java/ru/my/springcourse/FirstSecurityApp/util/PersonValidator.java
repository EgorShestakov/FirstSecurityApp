package ru.my.springcourse.FirstSecurityApp.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.my.springcourse.FirstSecurityApp.models.Person;
import ru.my.springcourse.FirstSecurityApp.services.PeopleService;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {

    private PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person personCreated = (Person) target;

        Optional<Person> person = peopleService.findByUsername(personCreated.getUsername());

        if(person.isPresent()) {
            errors.rejectValue("username", "", "Пользователь с таким именем уже существует");
        }
    }
}
