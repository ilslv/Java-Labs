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
                .compile("(\\w++)\\s*(((\\s*\\[\\s*])+\\s*(\\w+))|((\\w+)(\\s*\\[\\s*])+))\\s*=\\s*((new\\s+(\\w+)(\\s*\\[\\s*\\d+\\s*])+)|(\\{(.|)+}+))\\s*(?=;)")
                .matcher(input)
                .results()
                .forEach(array -> {
                    String arrayName = array.group(5) != null ? array.group(5) : array.group(7);
                    int sizeOfElement = getSizeOfElement(array.group(1));
                    int size;

                    //testing for one dimensional array initialized with {}
                    if (array.group(13) != null) {
                        //testing for single []
                        if (Pattern.compile("\\[\\s*]").matcher(array.group(2)).results().count() != 1) {
                            return;
                        }

                        //counting all ',' and adding 1
                        int numberOfElements = (int) array.group(13).chars().filter(ch -> ch == ',').count() + 1;
                        size = sizeOfArray(sizeOfElement, numberOfElements);
                    } else {
                        //testing for equal number of [] in left and right sides
                        if (Pattern.compile("\\[\\s*]").matcher(array.group(2)).results().count() !=
                                Pattern.compile("\\[\\s*(\\d+)\\s*]").matcher(array.group(10)).results().count()) {
                            return;
                        }

                        String arrayDimensions = array.group(10);
                        Stack<Integer> arrayDimsStack = new Stack<>();

                        //filling stack with array dimensions
                        Pattern
                                .compile("\\[\\s*(\\d+)\\s*]")
                                .matcher(arrayDimensions)
                                .results()
                                .forEach(dimension -> arrayDimsStack.push(Integer.valueOf(dimension.group(1))));

                        size = sizeOfArray(sizeOfElement, arrayDimsStack.pop());
                        size = arrayDimsStack
                                .stream()
                                .reduce(size, (result, numberOfElements) -> sizeOfArray(numberOfElements, result));
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
