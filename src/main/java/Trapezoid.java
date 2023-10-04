public class Trapezoid implements Method {
    private double a, b, e, h, i = 0;
    double prev_x, x, y;
    private int n;

    public Trapezoid(double a, double b, double e){
        this(a, b, e, 4);
    }
    public Trapezoid(double a, double b, double e, int n) {
        this.a = a;
        this.b = b;
        this.e = e;
        this.n = n;
        h = (b - a) / n;
    }

    public void resolve(){

        PrinterTable printer = new PrinterTable();
        System.out.println("n = " + n + "\n");
        System.out.print(printer.getHeader( "x_i", "y_i"));

        x = a;
        y = Function.f(x);
        System.out.print(printer.getLine(0, x, y));
        for(int j = 1; j <= n; j++){
            prev_x = x;
            x += h;
            y = Function.f(x);

            i += h * (Function.f(prev_x) + y) / 2;

            System.out.print(printer.getLine(j, x, y));
        }

        System.out.println("I = " + ((int)(i / e * 10)) * e / 10 + "\n");
    }

    public void run(){
        resolve();
        double i_prev;
        do {
            i_prev = i;
            i = 0;

            n *= 2;
            h = (b - a) / n;

            if(n > 1000000){
                System.out.println("Такой малой точности не достигнуть за вменяемое количество итераций!");
                break;
            }

            resolve();
        } while (!checkRunge(i_prev));
    }

    private boolean checkRunge(double i){
        double runge = Math.abs(i - this.i) / 3;


        System.out.println("Погрешность = " + runge + "  (" + (runge/i * 100) + "%)" + "\n");

        return runge <= e;
    }
}
