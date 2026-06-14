package com.bharath.PersonalNotes;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePage {

    @GetMapping("/")
    public String Home(HttpServletRequest httpServlet) {
        return "Welcome to Home Page " + httpServlet.getSession().getId();
    }


}
