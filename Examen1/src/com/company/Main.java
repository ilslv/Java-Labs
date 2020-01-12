package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static String readFromFile(String filePath) throws IOException {
        StringBuilder result = new StringBuilder();
        Files.lines(Paths.get(filePath)).forEach(s -> result.append(s).append("\n"));
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter output1 = new BufferedWriter(new FileWriter("output1.txt"));
        BufferedWriter output2 = new BufferedWriter(new FileWriter("output2.txt"));
        BufferedWriter output3 = new BufferedWriter(new FileWriter("output3.txt"));
        String input = readFromFile("input.txt");

        Utils.writeResult(input, output1, output2, output3);
    }
}
