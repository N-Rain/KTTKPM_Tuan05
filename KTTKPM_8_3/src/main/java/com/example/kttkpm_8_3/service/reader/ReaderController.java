package com.example.kttkpm_8_3.service.reader;

import com.example.kttkpm_8_3.service.book.JwtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/readers")
public class ReaderController {

    @Autowired
    private ReaderService readerService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private RedisService redisService;

    @GetMapping
    public List<ReaderEntity> getAllReaders() {
        return readerService.getAllReaders();
    }

    @GetMapping("/{id}")
    public ReaderEntity getReaderById(@PathVariable Long id) {
        return readerService.getReaderById(id).orElse(null);
    }

    @PostMapping
    public ReaderEntity createReader(@RequestBody ReaderEntity reader) {
        return readerService.createReader(reader);
    }

    @PutMapping("/{id}")
    public ReaderEntity updateReader(@PathVariable Long id, @RequestBody ReaderEntity updatedReader) {
        return readerService.updateReader(id, updatedReader);
    }

    @DeleteMapping("/{id}")
    public void deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody JwtRequest jwtRequest) {
        // Validate username and password (you may use authentication manager)

        // Generate JWT token
        String token = jwtUtil.generateToken(jwtRequest.getUsername());

        // Save token to Redis for logout functionality
        redisService.saveToken(token, jwtRequest.getUsername());

        return token;
    }

    @PostMapping("/logout")
    public void logout(@RequestHeader("Authorization") String token) {
        // Extract username from token
        String username = jwtUtil.extractUsername(token);

        // Remove token from Redis
        redisService.deleteToken(username);
    }
}
