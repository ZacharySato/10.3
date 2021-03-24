import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final int SIZE = 10;

    public static void main(final String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Сделка такая: вы нам целое число, а мы вам массивы:");
        int number;
        try {
            number = input.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Ввели что-то не то, будет ноль.");
            number = 0;
        }
        input.close();
        //сумма индекса и модификатора
        System.out.println(Arrays.toString(
                getFilledArray(SIZE, number, Integer::sum))
        );
        //произведение индекса и модификатора
        System.out.println(Arrays.toString(
                getFilledArray(SIZE, number, (x, y) -> x * y))
        );
        //для чётного индекса <(разделить на 2) плюс модификатор>, для нечётного <(квадрат индекса) минус модификатор>
        System.out.println(Arrays.toString(
                getFilledArray(SIZE, number, (x, y) -> {
                    if ((x % 2) == 0) {
                        return x / 2 + y;
                    } else {
                        return x * x - y;
                    }
                }))
        );
        //модификатор суммируется индекс раз
        System.out.println(Arrays.toString(
                getFilledArray(SIZE, number, (x, y) -> {
                    int n = y;
                    for (int i = 0; i <= x; i++) {
                        n += y;
                    }
                    return n;
                }))
        );
        //разность произведения и суммы
        System.out.println(Arrays.toString(
                getFilledArray(SIZE, number, (x, y) -> {
                    return x * y - (x + y);
                }))
        );

    }

    private static int[] getFilledArray(final int size, final int mod, final Transform transform) {
        int[] array = new int[size];
        for (int i = 0; i < array.length; i++) {
            array[i] = transform.process(i, mod);
        }
        return array;
    }


    @FunctionalInterface
    private interface Transform {
        int process(int index, int mod);
    }
}