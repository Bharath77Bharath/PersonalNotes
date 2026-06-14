package com.bharath.PersonalNotes.auth;


import com.bharath.PersonalNotes.user.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public Users registerUser(@RequestBody Users user) {
        return authService.registerUserService(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Users user) {
        return authService.verifyLogin(user);
    }

}
