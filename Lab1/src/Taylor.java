public class Taylor {

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
        return Math.pow(x, step) / factorial(step);
    }

    private static long factorial(int n) {
        long result = 1;
        for(;n > 0; n--){
            result *= n;
        }
        return result;
    }
}
