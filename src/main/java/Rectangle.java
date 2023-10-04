public class Rectangle implements Method{
    private double a, b, e, h, i_left = 0, i_right = 0, i_middle = 0;
    private int  n_right = 0, n_left = 0, n_middle = 0;
    double x, y, x_half;
    private int n;

    public Rectangle(double a, double b, double e){
        this(a, b, e, 4);
    }
    public Rectangle(double a, double b, double e, int n) {
        this.a = a;
        this.b = b;
        this.e = e;
        this.n = n;
        h = (b - a) / n;
    }

    public void resolve(){

        PrinterTable printer = new PrinterTable();
        System.out.println("n = " + n + "\n");
        System.out.print(printer.getHeader( "x_i", "y_i", "x_i - 1/2", "y_i - 1/2"));

        x = a;
        y = Function.f(x);
        i_left += h * y;
        System.out.print(printer.getLine(0, x, y, 0 ,0));
        for(int j = 1; j <= n; j++){
            x += h;
            y = Function.f(x);
            x_half = x - h/2;

            i_middle += h * Function.f(x_half);
            i_right += h * y;
            if(j != n)
                i_left += h * y;

            System.out.print(printer.getLine(j, x, y, x_half, Function.f(x_half)));
        }

        System.out.println("I левых = " + ((int)(i_left / e * 10)) * e / 10);
        System.out.println("I правых = " + ((int)(i_right / e * 10)) * e / 10);
        System.out.println("I средних = " + ((int)(i_middle / e * 10)) * e / 10 + "\n");
    }

    public void run(){
        resolve();
        double i[] = new double[3];
        do {
            i[0] = i_left;
            i[1] = i_right;
            i[2] = i_middle;
            i_left = 0;
            i_right = 0;
            i_middle = 0;
            n *= 2;
            h = (b - a) / n;

            if(n > 1000000){
                System.out.println("Такой малой точности не достигнуть за вменяемое количество итераций!");
                break;
            }

            resolve();
        } while (!checkRunge(i[0], i[1], i[2]));
    }

    private boolean checkRunge(double i_left, double i_right, double i_middle){
        double runge_left, runge_right, runge_middle;
        runge_left = Math.abs(i_left - this.i_left) / 3;
        runge_right = Math.abs(i_right - this.i_right) / 3;
        runge_middle = Math.abs(i_middle - this.i_middle) / 3;

        System.out.println("Погрешность левых = " + runge_left + "  (" + (runge_left/i_left * 100) + "%)");
        System.out.println("Погрешность правых = " + runge_right + "  (" + (runge_right/i_right * 100) + "%)");
        System.out.println("Погрешность средних = " + runge_middle + "  (" + (runge_middle/i_middle * 100) + "%)" + "\n");

        if(runge_middle < e && n_middle == 0)
            n_middle = n;

        if(runge_right < e && n_right == 0)
            n_right = n;

        if(runge_left < e && n_left == 0)
            n_left = n;

        System.out.println("n левых = " + n_left);
        System.out.println("n правых = " + n_right);
        System.out.println("n средних = " + n_middle);


        return Math.max(Math.max(runge_left, runge_right), runge_middle) <= e;
    }
}
