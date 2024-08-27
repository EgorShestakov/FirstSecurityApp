package ru.my.springcourse.FirstSecurityApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.my.springcourse.FirstSecurityApp.secutiry.PersonDetails;
import ru.my.springcourse.FirstSecurityApp.services.AdminService;

@Controller
public class HelloController {

    private AdminService adminService;

    @Autowired
    public HelloController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/getUser")
    public String getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();

        System.out.println(personDetails.getPerson());
        return "hello";
    }

    @GetMapping("/admin")
    public String admin() {
        adminService.doAdmin();
        return "admin";
    }
}
