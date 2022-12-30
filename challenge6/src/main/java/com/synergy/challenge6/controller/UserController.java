package com.synergy.challenge6.controller;

import com.synergy.challenge6.model.User;
import com.synergy.challenge6.service.UserService;
import com.synergy.challenge6.view.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<Map> index() {
        Map response = userService.getAll();

        return new ResponseEntity<>(
                response,
                HttpStatusCode.valueOf((Integer) response.get(APIResponse.STATUS))
        );
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Map> update(@PathVariable long userId, User user) {
        Map response = userService.updateUser(userId, user);

        return new ResponseEntity<>(
                response,
                HttpStatusCode.valueOf((Integer) response.get(APIResponse.STATUS))
        );
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Map> delete(@PathVariable long userId) {
        Map response = userService.deleteUser(userId);

        return new ResponseEntity<>(
                response,
                HttpStatusCode.valueOf((Integer) response.get(APIResponse.STATUS))
        );
    }
}
