package me.i.springapi.api.controller;

import jakarta.validation.Valid;
import me.i.springapi.api.service.DataBaseWorker;
import me.i.springapi.api.model.User;
import me.i.springapi.api.service.DataBaseException;
import me.i.springapi.api.service.FileWorker;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
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
    FileWorker fileWorker = new FileWorker();

    @GetMapping
    @ResponseBody
    public ResponseEntity<User> getFromDB(@RequestParam("login") String login) {
        ResponseEntity<User> responseEntity = null;
        try {
            Thread.sleep(responseDelayTime());
            User user = dbWorker.selectQuery(login);
            responseEntity = new ResponseEntity<>(user, HttpStatus.OK);
            fileWorker.write(user);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (DataBaseException | SQLException e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/file")
    @ResponseBody
    public ResponseEntity<User> getFromFile() {
        ResponseEntity<User> responseEntity = null;
        User user = new User();
        FileWorker fileWorker = new FileWorker();
        int stringNumber = (int) (Math.random() * 10);
        try {
            Thread.sleep(responseDelayTime());
            user = fileWorker.read(stringNumber);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> post(@Valid @RequestBody User user) {
        ResponseEntity<String> responseEntity = null;
        try {
            Thread.sleep(responseDelayTime());
            user.setDate(new Timestamp(System.currentTimeMillis()));
            dbWorker.insertQuery(user);
            responseEntity = new ResponseEntity<>("Insert successful.", HttpStatus.OK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            responseEntity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return responseEntity;
    }
}
