package com.company;

public class Main {

    public static void main(String[] args) {
        Matrix m = new Matrix(3, 3);
        m.set(2, 2, 0);
        System.out.println(m.toString());
        m.sortByRowSum();
        System.out.println("\n" + m.toString());
    }
}
