package com.example.cache.Controllers;


import com.example.cache.Models.UserLoginViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private static final String USER_SESSION_KEY = "loggedUser";

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<?> login(@RequestBody UserLoginViewModel user, HttpServletRequest request) {
        if(isUserLoggedIn(request))
            return ResponseEntity.ok("LoggedIn from session");

        request.getSession().setAttribute(USER_SESSION_KEY, user.getUserName());
        return ResponseEntity.ok("LoggedIn and saved session");


    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<?> testLogin(HttpServletRequest request) {
        if(isUserLoggedIn(request))
            return ResponseEntity.ok("LoggedIn from session");

        request.getSession().setAttribute(USER_SESSION_KEY, "teset");
        return ResponseEntity.ok("Logged in and saved session");


    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_SESSION_KEY);
        return ResponseEntity.ok("Logout succeed");


    }

    private boolean isUserLoggedIn(HttpServletRequest request){
        Object loggedUser = request.getSession().getAttribute(USER_SESSION_KEY);
        return loggedUser != null;
    }
}
