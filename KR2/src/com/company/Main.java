package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {
    public static String readFromFile(String filePath) throws IOException {
        StringBuilder result = new StringBuilder();
        Files.lines(Paths.get(filePath)).forEach(s -> result.append(s).append("\n"));
        return result.toString();
    }

    public static int sizeOfArray(int sizeOfElement, int numberOfElements) {
        return (int) Math.ceil((sizeOfElement * numberOfElements + 28) / 8.) * 8;
    }

    public static int getSizeOfElement(String str) {
        int sizeOfElement = 0;
        switch (str) {
            case ("byte"):
                sizeOfElement = 1;
                break;
            case ("short"):
            case ("char"):
                sizeOfElement = 2;
                break;
            case ("int"):
            case ("float"):
                sizeOfElement = 4;
                break;
            case ("long"):
            case ("double"):
                sizeOfElement = 8;
                break;
        }
        return sizeOfElement;
    }

    public static void main(String[] args) throws IOException {
        String input = readFromFile("arrays.in");
        BufferedWriter output = new BufferedWriter(new FileWriter("arrays.out"));

        Pattern
                .compile("(\\w+)(\\s*\\[\\s*])*\\s*(\\w+)(\\s*\\[\\s*])*\\s*=\\s*((new\\s*(\\w+)(\\s*\\[\\s*\\d+\\s*])+)|(\\{(.|\n)+}+))\\s*(?=;)")
                .matcher(input)
                .results()
                .forEach(array -> {
                    String arrayName = array.group(3);
                    int sizeOfElement = getSizeOfElement(array.group(1));
                    int size;

                    if (array.group(9) != null) {
                        int numberOfElements = (int) array.group(9).chars().filter(ch -> ch == ',').count() + 1;
                        size = sizeOfArray(sizeOfElement, numberOfElements);
                    } else {
                        String arrayDimensions = array.group(5);
                        Stack<Integer> arrayDimsStack = new Stack<>();

                        Pattern
                                .compile("\\[\\s*(\\d+)\\s*]")
                                .matcher(arrayDimensions)
                                .results()
                                .forEach(dimension -> arrayDimsStack.push(Integer.valueOf(dimension.group(1))));

                        size = sizeOfArray(sizeOfElement, arrayDimsStack.pop());
                        size = arrayDimsStack
                                .stream()
                                .reduce(size, (result, numberOfElements) -> (int) Math.ceil((numberOfElements * result + 28) / 8.) * 8);
                    }

                    try {
                        output.append(arrayName).append("\t—\t").append(String.valueOf(size)).append("\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });

        output.close();
    }
}
