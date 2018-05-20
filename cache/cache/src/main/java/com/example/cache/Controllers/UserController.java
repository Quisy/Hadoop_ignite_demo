package com.example.cache.Controllers;


import com.example.cache.Models.UserLoginViewModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private static final String USER_SESSION_KEY = "loggedUser";

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity login(@RequestBody UserLoginViewModel user, HttpServletRequest request) {
        if(isUserLoggedIn(request))
            return ResponseEntity.ok(true);

        request.getSession().setAttribute(USER_SESSION_KEY, user.getUserName());
        return ResponseEntity.ok(true);

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<?> checkLogin(HttpServletRequest request) {
        if(isUserLoggedIn(request))
            return ResponseEntity.ok(true);

        return ResponseEntity.ok(false);


    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout(HttpServletRequest request) {
        request.getSession().removeAttribute(USER_SESSION_KEY);
        return ResponseEntity.ok(true);


    }

    private boolean isUserLoggedIn(HttpServletRequest request){
        Object loggedUser = request.getSession().getAttribute(USER_SESSION_KEY);
        return loggedUser != null;
    }
}
