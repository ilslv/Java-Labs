package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Student> students = new ArrayList<Student>();

        //generateFile(students);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students.dat"));
        students = (List<Student>) ois.readObject();

        printExcellentStudents(students, new FileOutputStream("excellentStudents.txt"));
        printBadStudents(students, new FileOutputStream("badStudents.txt"));
        sortByName(students, new FileOutputStream("sortedByNameStudents.txt"));
        sortByAverageMark(students, new FileOutputStream("sortedByAverageMarkStudents.txt"));
    }

    public static void generateFile(List<Student> students) throws IOException {
        students.add(new Student("Соловьёв Илья"));
        students.add(new Student("Курч Алексей"));
        students.add(new Student("Шевко Майя"));
        students.add(new Student("Малевич Никита"));
        students.add(new Student("Зеликов Андрей"));

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.dat"));
        oos.writeObject(students);
        oos.flush();
    }

    public static void printExcellentStudents(List<Student> students, OutputStream out) throws IOException {
        out.write("Excellent students:\n".getBytes());
        students.forEach(
                (Student student) -> {
                    try {
                        if (student.averageMark() >= 6) out.write(("\t" + student.toString()).getBytes());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
        );
    }

    public static void printBadStudents(List<Student> students, OutputStream out) throws IOException {
        out.write("Bad students:\n".getBytes());
        students.forEach(
                (student) -> {
                    try {
                        if (student.averageMark() < 6) out.write(("\t" + student.toString()).getBytes());
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
        );
    }

    public static void sortByName(List<Student> students, OutputStream out) throws IOException {
        out.write("Sorted by name:\n".getBytes());
        students.sort(
                (student1, student2) -> student1.name.compareTo(student2.name)
        );
        students.forEach(student -> {
            try {
                out.write(("\t" + student).getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }

    public static void sortByAverageMark(List<Student> students, OutputStream out) throws IOException {
        out.write("Sorted by average mark:\n".getBytes());
        students.sort(
                (student1, student2) -> student1.averageMark() < student2.averageMark() ? -1 : 0
        );
        students.forEach(student -> {
            try {
                out.write(("\t" + student).getBytes());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        });
    }
}
