package com.zuhriddin.service.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class UtilityTxt {
    public static void write(String str, String PATH) {

        try (FileWriter fileWriter = new FileWriter(PATH)) {
            fileWriter.write(str);
        } catch (IOException e) {
            throw new RuntimeException("File not found.");

        }
    }

    public static String read(String PATH) {

        try(FileReader fileReader = new FileReader(PATH);
            BufferedReader bufferedReader = new BufferedReader(fileReader) ) {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("File not found.");
        }
    }

}
