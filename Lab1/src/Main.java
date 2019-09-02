import java.text.DecimalFormat;
import java.text.NumberFormat;
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
        System.out.printf("%.3f %.3f", Taylor.calculate(k, x), Math.pow(Math.E, x));
    }
}
