package ru.my.springcourse.FirstSecurityApp.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.my.springcourse.FirstSecurityApp.models.Person;
import ru.my.springcourse.FirstSecurityApp.services.PeopleService;
import ru.my.springcourse.FirstSecurityApp.util.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final PersonValidator personValidator;

    private final PeopleService peopleService;

    @Autowired
    public AuthController(PersonValidator personValidator, PeopleService peopleService) {
        this.personValidator = personValidator;
        this.peopleService = peopleService;
    }

    @GetMapping("/login")
    public String auth() {
        return "auth/auth";
    }

    @GetMapping("/registration")
    public String registrationPage(Model model, @ModelAttribute("person") Person person) {
        return "auth/registrationPage";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors())
            return "auth/registrationPage";

        peopleService.save(person);
        return "redirect:/auth/login";
    }
}
