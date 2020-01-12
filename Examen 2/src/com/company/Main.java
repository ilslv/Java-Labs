package com.company;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static String readFromFile(String filePath) throws IOException {
        StringBuilder result = new StringBuilder();
        Files.lines(Paths.get(filePath)).forEach(s -> result.append(s).append("\n"));
        return result.toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedWriter output1 = new BufferedWriter(new FileWriter("output1.txt"));
        BufferedWriter output2 = new BufferedWriter(new FileWriter("output2.txt"));
        BufferedWriter output3 = new BufferedWriter(new FileWriter("logfile.txt"));
        File directory = new File("C:\\");
        String input = readFromFile("input.txt");
        List<String> commands = new ArrayList<>();

        Matcher matcher = Pattern.compile("((cd\\s+(.+))|(dir\\s*)|(cd\\.\\.\\s*))(?=;)").matcher(input);

        while (matcher.find()) {
            if (matcher.group(2) != null) {

                commands.add("cd " + matcher.group(3));
                File newDir = new File(matcher.group(3));
                if (newDir.isDirectory()) {
                    output3.append(matcher.group(1)).append("\t-\tsuccess\n");
                    directory = newDir;
                } else {
                    output3.append(matcher.group(1)).append("\t-\tfail\n");
                }

            } else if (matcher.group(4) != null) {

                commands.add("dir");
                output2.append(directory.getAbsolutePath()).append("\n");
                for (String entry : directory.list()) {
                    output2.append("\t").append(entry).append("\n");
                }

            } else if (matcher.group(5) != null) {

                commands.add("cd..");
                try {
                    directory = new File(directory.getParent());
                    output3.append(matcher.group(1)).append("\t-\tsuccess\n");
                } catch (NullPointerException e) {
                    output3.append(matcher.group(1)).append("\t-\tfail\n");
                }

            }
        }

        commands
                .stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .forEach(command -> {
                    try {
                        output1.append(command).append("\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        output1.close();
        output2.close();
        output3.close();
    }
}
