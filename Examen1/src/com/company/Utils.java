package com.company;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Utils {
    private static void outputChars(List<Character> list, BufferedWriter output) throws IOException {
        for (int i = 0; i < list.size(); i++) {
            output.append(list.get(i));
            while (i < list.size() - 1 && list.get(i) == list.get(i + 1)) {
                i++;
            }
        }
        output.close();
    }

    public static void writeResult(String input, BufferedWriter output1, BufferedWriter output2, BufferedWriter output3) throws IOException {
        List<String> wordsArray = new ArrayList<>();
        List<Pair<String, Integer>> result = new ArrayList<>();

        Pattern.compile("\\b\\w+\\b")
                .matcher(input)
                .results()
                .forEach(s -> wordsArray.add(s.group()));
        wordsArray.sort(String::compareTo);

        for (int i = 0; i < wordsArray.size(); i++) {
            result.add(new Pair<>(wordsArray.get(i), 1));
            while (i < wordsArray.size() - 1 && wordsArray.get(i).equals(wordsArray.get(i + 1))) {
                result.get(result.size() - 1).second++;
                i++;
            }
        }

        result
                .stream()
                .sorted((p1, p2) -> (int) p2.second - (int) p1.second)
                .forEach(p -> {
                    try {
                        output1.append(p.first).append('(').append(String.valueOf(p.second)).append(") ");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        output1.close();

        List<Character> alphabetical = new ArrayList<>();
        List<Character> delimiters = new ArrayList<>();

        input.chars().forEach(c -> {
            if (Character.isLetterOrDigit(c)) {
                alphabetical.add((char) c);
            } else {
                delimiters.add((char) c);
            }
        });

        alphabetical.sort((c1, c2) -> c1 - c2);
        outputChars(alphabetical, output2);
        delimiters.sort((c1, c2) -> c2 - c1);
        outputChars(delimiters, output3);

    }
}
