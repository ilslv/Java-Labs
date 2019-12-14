package com.company;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Stack;
import java.util.regex.Pattern;

public class Main {
    public static String readFromFile(String filePath) throws IOException {
        StringBuilder result = new StringBuilder();
        Files.lines(Paths.get(filePath)).forEach(s -> result.append(s).append("\n"));
        return result.toString();
    }

    public static int sizeOfArray(int elementSize, int numberOfElements) {
        return (int) Math.ceil((elementSize * numberOfElements + 28) / 8.) * 8;
    }

    public static int getSizeOfElement(String str) throws RuntimeException {
        int sizeOfElement;
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
            default:
                throw new RuntimeException("Type not found!");
        }
        return sizeOfElement;
    }

    public static void main(String[] args) throws IOException {
        String input = readFromFile("arrays.in");
        BufferedWriter output = new BufferedWriter(new FileWriter("arrays.out"));

        Pattern.compile("(\\w+)\\s+(((\\s*\\[\\s*])+\\s*(\\w+))|((\\w+)(\\s*\\[\\s*])+))\\s*=\\s*((new\\s+\\1(\\s*\\[\\s*\\d+\\s*])+)|(\\{\\s*\\w+\\s*(,\\s*\\d+\\s*)*\\s*}))\\s*(?=;)")
                .matcher(input)
                .results()
                .forEach(array -> {
                    String arrayName = array.group(5) != null ? array.group(5) : array.group(7);
                    String rightSide = array.group(12);
                    String leftSide = array.group(2);

                    int size;
                    int elementSize;

                    try {
                        elementSize = getSizeOfElement(array.group(1));
                    } catch (RuntimeException e){
                        return;
                    }

                    if (rightSide != null) {
                        int numberOfParenthesis = (int) leftSide.chars().filter(c -> c == '[').count();
                        if (numberOfParenthesis > 1) {
                            return;
                        }

                        int numberOfElements = (int) rightSide.chars().filter(c -> c == ',').count() + 1;
                        size = sizeOfArray(elementSize, numberOfElements);
                    } else {
                        rightSide = array.group(10);

                        int rightSideParenthesis = (int) rightSide.chars().filter(c -> c == '[').count();
                        int leftSideParenthesis = (int ) leftSide.chars().filter(c -> c == '[').count();
                        if (rightSideParenthesis != leftSideParenthesis) {
                            return;
                        }

                        Stack<Integer> arrayDimsStack = new Stack<>();

                        Pattern.compile("\\[\\s*(\\d+)\\s*]")
                                .matcher(rightSide)
                                .results()
                                .forEach(dimension -> arrayDimsStack.push(Integer.valueOf(dimension.group(1))));

                        size = arrayDimsStack
                                .stream()
                                .reduce(elementSize, Main::sizeOfArray);
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
