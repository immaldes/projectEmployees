package com.example.utils;

import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {

    public static void write(String string){

        try (FileWriter file = new FileWriter("src/persistence/employees.json")){
            file.write(string);
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
