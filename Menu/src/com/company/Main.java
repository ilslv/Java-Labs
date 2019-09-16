package com.company;

public class Main {

    public static void main(String[] args) {
        while (true) {
            try {
                System.out.println(Menu.Start());
            } catch (Exception ex) {
                return;
            }
        }
    }
}
