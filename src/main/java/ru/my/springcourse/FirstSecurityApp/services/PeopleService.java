package ru.my.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.my.springcourse.FirstSecurityApp.models.Person;
import ru.my.springcourse.FirstSecurityApp.repositories.PeopleRepository;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public Optional<Person> findByUsername(String username) {
        return peopleRepository.findByUsername(username);
    }
    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }
}
