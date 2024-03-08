package com.example.kttkpm_8_3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisService redisService;

    @PostMapping("/login")
    public String login(@RequestBody AuthenticationRequest authenticationRequest) {
        // Implement your authentication logic here (e.g., validate username and password)
        // For simplicity, assume successful authentication for any input
        String token = jwtUtil.generateToken(authenticationRequest.getUsername());
        redisService.storeToken(authenticationRequest.getUsername(), token);
        return token;
    }

    // Other CRUD endpoints for books
}

