import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PrinterTable {
    private int accuracy = 3;
    private float acc = (float) Math.pow(10, accuracy);

    /**
     * Печатает заголовок таблицы
     * @param isConsole 1 если вывод в консоль, 0 если вывод в файл
     * @param n если вывод в консоль, то номер файла, иначе не используется
     * @param args аргументы заголовка
     */
    public void printHeader(boolean isConsole, int n, String... args) throws IOException{
        if(!isConsole){
            FileWriter writer = new FileWriter("build/res/out" + n + ".txt", true);
            writer.write(getHeader(args));
            writer.close();
        } else
            System.out.print(getHeader(args));
    }

    /**
     * Печатет строку таблицы
     * @param isConsole 1 если вывод в консоль, 0 если вывод в файл
     * @param n если вывод в консоль, то номер файла, иначе не используется
     * @param i номер строки
     * @param args аргументы заголовка
     */
    public void printLine(boolean isConsole, int n, int i, double... args) throws IOException{
        if(!isConsole){
            FileWriter writer = new FileWriter("build/res/out" + n + ".txt", true);
            writer.write(getLine(i, args));
            writer.close();
        } else
            System.out.print(getLine(i, args));
    }

    public String getHeader(String... args){
        String str = "";

        str += "+" + "-".repeat((args.length + 1) * 16 - 1) + "+\n";

        str += String.format("|%-15s", "i");
        str += Arrays.stream(args).map(x->String.format("|%-15s", x)).collect(Collectors.toList()).toString()
                .replace("[", "").replace(", ", "").replace("]", "");
        str += "|\n";

        str += "+" + ("-".repeat(15) + "+").repeat((args.length + 1)) + "\n";

        return str;
    }

    public String getLine(int i, double... args) {
        String str = "";

        List<String> argsString = Arrays.stream(args).mapToObj(x -> String.valueOf(Math.round(x * acc) / acc)).collect(Collectors.toList());

        str += String.format("|%-15s", i);
        str += Arrays.stream(argsString.toArray()).map(x -> String.format("|%-15s", x)).collect(Collectors.toList()).toString()
                .replace("[", "").replace(", ", "").replace("]", "");
        str += "|\n";

        str += "+" + ("-".repeat(15) + "+").repeat((args.length + 1)) + "\n";

        return str;
    }
}
