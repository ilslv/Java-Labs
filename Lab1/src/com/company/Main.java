package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        int k;
        double x;
        Scanner in = new Scanner(System.in);
        try{
            System.out.println("Enter k: ");
            k = in.nextInt();
            System.out.println("Enter x: ");
            x = in.nextDouble();
        }catch (Throwable ex){
            System.out.println("Error occurred while reading input!");
            return;
        }
        System.out.printf("Approximation:\t\t\t%.3f\nUsing math functions:\t%.3f", Taylor.calculate(k, x), Math.pow(Math.E, x));
    }
}
