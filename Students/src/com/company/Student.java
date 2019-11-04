package com.company;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Student implements Comparable<Student>, Serializable {
    private static int numberOfStudents;

    public String name;
    public int id;
    public List<Session> sessions;

    public Student(String name) {
        this.name = name;
        sessions = new ArrayList<Session>();

        for (int i = 0; i < (int) (1.0 + Math.random() * 3.0); i++) {
            Session session = new Session();
            session.subjects = new HashMap<Subjects, Integer>(Subjects.size);
            for (Subjects subject : Subjects.values()) {
                session.subjects.put(subject, (int) (4.0 + Math.random() * 6.0));
            }
            sessions.add(session);
        }

        this.id = numberOfStudents;
        numberOfStudents++;
    }

    @Override
    public int compareTo(Student otherStudent) {
        return this.name.compareTo(otherStudent.name) != 0 ?
                this.name.compareTo(otherStudent.name) : this.id - otherStudent.id;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(name + "\t").append(id + "\t").append(this.averageMark() + "\n");
        return result.toString();
    }

    public double averageMark() {
        double sum = 0;
        int numberOfMarks = 0;
        for (Session session : sessions) {
            Pair<Integer, Integer> stats = session.getStats();
            sum += stats.first;
            numberOfMarks += stats.second;
        }
        return (double) sum / numberOfMarks;
    }
}
