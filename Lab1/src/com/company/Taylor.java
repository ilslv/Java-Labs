package com.company;

public class Taylor {

    private static long factorialStep = 1;

    public static double calculate(int k, double x) {
        int i = 0;
        double currentStep = 0.0, result = 0.0;
        do {
            result += currentStep;
            currentStep = func(i, x);
            i++;
        }while(Math.abs(currentStep) > Math.pow(10, -1 * k));
        return result;
    }

    private static double func(int step, double x) {
        factorialStep *= step > 0 ? step : 1;
        return Math.pow(x, step) / factorialStep;
    }
}
