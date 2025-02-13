package me.i.springapi.api.controller;

import me.i.springapi.api.model.GetOutput;
import me.i.springapi.api.model.PostInput;
import me.i.springapi.api.model.PostOutput;
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
    public CompletableFuture<ResponseEntity<GetOutput>> get() {
        try {
            Thread.sleep(responseDelayTime());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<GetOutput> response = new ResponseEntity<>(new GetOutput("Login1", "ok"), HttpStatus.OK);
        return CompletableFuture.completedFuture(response);
    }

    @PostMapping
    @Async
    @ResponseBody
    public CompletableFuture<ResponseEntity<PostOutput>> post(@RequestBody PostInput postInput) {
        try {
            Thread.sleep(responseDelayTime());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        ResponseEntity<PostOutput> response = new ResponseEntity<>(new PostOutput(postInput.getLogin(), postInput.getPassword(), LocalDateTime.now()), HttpStatus.OK);
        return CompletableFuture.completedFuture(response);
    }
}
