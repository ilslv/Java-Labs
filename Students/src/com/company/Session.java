package com.company;

import java.io.Serializable;
import java.util.Map;

public class Session implements Serializable {
    public Map<Subjects, Integer> subjects;

    public Pair<Integer, Integer> getStats() {
        int sum = 0;
        for (Map.Entry<Subjects, Integer> subject : subjects.entrySet()) {
            sum += subject.getValue();
        }
        return new Pair<Integer, Integer>(sum, subjects.size());
    }

    public double average() {
        Pair<Integer, Integer> stats = getStats();
        return (double) stats.first / stats.second;
    }
}