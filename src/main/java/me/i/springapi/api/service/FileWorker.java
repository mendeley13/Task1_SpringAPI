package me.i.springapi.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.i.springapi.api.model.User;

import java.io.*;


public class FileWorker {

    public void write(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./files/output.txt", true));
             BufferedWriter newLine = new BufferedWriter(new FileWriter("./files/output.txt", true))) {
            objectMapper.writeValue(writer, user);
            newLine.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User read(int lineNumber) {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        try (BufferedReader reader = new BufferedReader(new FileReader("./files/users.txt"))) {
            for (int i = 0; i < lineNumber; i++)
                reader.readLine();
            user = objectMapper.readValue(reader.readLine(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
