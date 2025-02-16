package me.i.springapi.api.controller;

import me.i.springapi.api.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

@Configuration
@EnableAsync
@RestController
@RequestMapping("/users")
public class Controller {

    private long responseDelayTime() {
        return 1000 + (long) (Math.random() * 1000);
    }

    @GetMapping
    @Async
    @ResponseBody
    public CompletableFuture<ResponseEntity<User>> get() {
        try {
            Thread.sleep(responseDelayTime());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        User user = new User();
        user.setLogin("Login1");
        user.setStatus("ok");
        ResponseEntity<User> response = new ResponseEntity<>(user, HttpStatus.OK);
        return CompletableFuture.completedFuture(response);
    }


    @PostMapping
    @Async
    @ResponseBody
    public CompletableFuture<ResponseEntity<User>> post(@RequestBody User user) {
        try {
            Thread.sleep(responseDelayTime());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        user.setDate(LocalDateTime.now());
        ResponseEntity<User> response = new ResponseEntity<>(user, HttpStatus.OK);
        return CompletableFuture.completedFuture(response);


    }
}
