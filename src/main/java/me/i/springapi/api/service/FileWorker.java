package me.i.springapi.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.i.springapi.api.model.User;

import java.io.*;


public class FileWorker {
    private String readFilePath = "/home/igor/CreateDockerImage/users.txt";
    private String writeFilePath = "/home/igor/CreateDockerImage/output.txt";

    public void write(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(writeFilePath, true));
             BufferedWriter newLine = new BufferedWriter(new FileWriter(writeFilePath, true))) {
            objectMapper.writeValue(writer, user);
            newLine.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public User read(int lineNumber) {
        ObjectMapper objectMapper = new ObjectMapper();
        User user = new User();
        try (BufferedReader reader = new BufferedReader(new FileReader(readFilePath))) {
            for (int i = 0; i < lineNumber; i++)
                reader.readLine();
            user = objectMapper.readValue(reader.readLine(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
