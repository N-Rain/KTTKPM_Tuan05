package com.example.kttkpm_8_3.service.book;

import com.example.kttkpm_8_3.service.reader.RedisService;
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

    @GetMapping
    public List<BookEntity> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookEntity getBookById(@PathVariable Long id) {
        return bookService.getBookById(id).orElse(null);
    }

    @PostMapping
    public BookEntity createBook(@RequestBody BookEntity book) {
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public BookEntity updateBook(@PathVariable Long id, @RequestBody BookEntity updatedBook) {
        return bookService.updateBook(id, updatedBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
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
