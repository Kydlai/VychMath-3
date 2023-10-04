public class Function {
    public static double a = 3, b = 5, c = 3, d = -6;
    public static double f(double x){
        return a * Math.pow(x, 3) + b * Math.pow(x, 2) + c * x + d;
    }

    public static double F(double x){
        return a/4 * Math.pow(x, 4) + b/3 * Math.pow(x, 3) + c/2 * Math.pow(x, 2) + d * x;
    }

    public static double getI(double a, double b){
        return F(b) - F(a);
    }

    public static void setFunction(int n){
        switch (n){
            case 1 -> {a = 3; b = 5; c = 3; d = -6;}
            case 2 -> {a = 4; b = -5; c = 6; d = -7;}
            case 3 -> {a = 3; b = -2; c = 5; d = -9;}
        }
    }
}
