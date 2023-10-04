import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Method method = new Trapezoid(0, 0, 0);
        int number;
        double a, b, e;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите функцию:\n" +
                    "1. 3x^3 + 5x^2 + 3x - 6\n" +
                    "2. 4x^3 - 5x^2 + 6x - 7\n" +
                    "3. 3x^3 - 2x^3 + 5x - 9");
            try {
                number = Integer.parseInt(scanner.nextLine());
                if(number > 3 || number < 1)
                    throw new IOException();
                Function.setFunction(number);
                break;
            }
            catch (IOException | NumberFormatException ex){
                System.err.println("Некорректное значение. Повторите ввод");
            }
        }

        while (true) {
            System.out.println("Выберите метод:\n" +
                    "1. Метод прямоугольников\n" +
                    "2. Метод трапеций\n" +
                    "3. Метод Симпсона");
            try {
                number = Integer.parseInt(scanner.nextLine());
                if(number > 3 || number < 1)
                    throw new IOException();
                break;
            }
            catch (IOException | NumberFormatException ex){
                System.err.println("Некорректное значение. Повторите ввод");
            }
        }

        while (true){
            System.out.println("Введите пределы интегрирования a и b:");
            try {
                a = Double.parseDouble(scanner.next());
                b = Double.parseDouble(scanner.next());
                break;
            }
            catch (NumberFormatException ex){
                System.err.println("Некорректное значение. Повторите ввод");
            }
        }

        while (true){
            System.out.println("Введите точность e:");
            try {
                e = Double.parseDouble(scanner.next());
                if(e <= 0)
                    throw new NumberFormatException();
                break;
            }
            catch (NumberFormatException ex){
                System.err.println("Некорректное значение. Повторите ввод");
            }
        }

        switch (number){
            case 1 -> method = new Rectangle(a, b, e);
            case 2 -> method = new Trapezoid(a, b, e);
            case 3 -> method = new Simpson(a, b, e);
        }

        method.run();

        //System.out.println("Точное значение I = " + (Function.F(b) - Function.F(a)));
    }
}