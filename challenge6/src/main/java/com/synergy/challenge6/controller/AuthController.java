package com.synergy.challenge6.controller;

import com.synergy.challenge6.model.User;
import com.synergy.challenge6.model.dto.LoginDto;
import com.synergy.challenge6.service.UserService;
import com.synergy.challenge6.view.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("login")
    public ResponseEntity<Map> login(@RequestBody LoginDto loginData) {
        Map map = userService.login(loginData.getEmail(),loginData.getPassword());
        return new ResponseEntity<Map>(
                map,
                HttpStatusCode.valueOf((Integer) map.get(APIResponse.STATUS))
        );
    }

    @PostMapping("signup")
    public ResponseEntity<Map> register(User user) {
        Map response = userService.createUser(user);

        return new ResponseEntity<>(
                response,
                HttpStatusCode.valueOf((Integer) response.get(APIResponse.STATUS))
        );
    }
}
