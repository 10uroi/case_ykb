package com.ykb.vacation.api;

import com.ykb.vacation.dto.VoidResponse;
import com.ykb.vacation.entity.User;
import com.ykb.vacation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserAPI {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<VoidResponse> create(@RequestBody User user, @RequestParam String lang) {
        return ResponseEntity.ok(userService.create(user));
    }

}
