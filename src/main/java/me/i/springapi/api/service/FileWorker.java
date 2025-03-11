package me.i.springapi.api.service;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileWorker {
    public static void main(String[] args) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt",true));
            writer.write("1234");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(String input) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt",true));
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
