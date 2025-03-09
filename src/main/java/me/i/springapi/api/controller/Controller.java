package me.i.springapi.api.controller;

import jakarta.validation.Valid;
import me.i.springapi.api.service.DataBaseWorker;
import me.i.springapi.api.model.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;


@Configuration
@EnableAsync
@RestController
@RequestMapping("/users")
public class Controller {
    private long responseDelayTime() {
        return 1000 + (long) (Math.random() * 1000);
    }

    DataBaseWorker dbWorker = new DataBaseWorker();

    @GetMapping
    @ResponseBody
    public ResponseEntity<User> get(@RequestParam("login") String login) {
        try {
            Thread.sleep(responseDelayTime());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        User user = dbWorker.selectQuery(login);
        ResponseEntity<User> responseEntity;
        if (user.getLogin() == null)
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        else responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
        return responseEntity;
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> post(@Valid @RequestBody User user) {
        try {
            Thread.sleep(responseDelayTime());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        user.setDate(new Timestamp(System.currentTimeMillis()));
        int rawsAdded = dbWorker.insertQuery(user);
        ResponseEntity<String> responseEntity;
        if (rawsAdded == 0)
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        else responseEntity = new ResponseEntity<>("{\n\"rawsAdded\": " + rawsAdded + "\n}", HttpStatus.OK);
        return responseEntity;
    }
}
