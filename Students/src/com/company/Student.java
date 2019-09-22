package com.company;

import java.util.List;

public class Student implements Comparable<Student> {
    public String name;
    public int id;
    public List<Session> sessions;

    @Override
    public int compareTo(Student otherStudent) {
        return this.name.compareTo(otherStudent.name) != 0 ?
                this.name.compareTo(otherStudent.name) : this.id - otherStudent.id;
    }
}
