
import java.util.Random;

import java.util.concurrent.*;


public class Main {
    public static void main(String[] args) {
        final int arraySize = 1000103;
        final int START = 0;
        final int[] newArray = generateArray(arraySize, START);
        long value1 = System.currentTimeMillis();

        System.out.println(value1 + " до ");
        long value2 = System.currentTimeMillis();
        System.out.println(value2 + " после");
        System.out.println("время выполнения " + (value2 - value1));
        System.out.println("summa = " + sumAllValues(newArray) + "\n");

        ForkJoinPool fjp = new ForkJoinPool();
        long value3 = System.currentTimeMillis();
        System.out.println(value3 + " до ");
        SummElementsArray sum = new SummElementsArray(newArray, START, arraySize);
        int summa = fjp.invoke(sum);
        long value4 = System.currentTimeMillis();
        System.out.println(value4 + " после");
        System.out.println("время выполнения " + (value4 - value3));
        System.out.println("summa = " + summa);
    }


    public static int[] generateArray(int size, int start) {
        Random random = new Random();
        int[] arrayInt = new int[size];
        for (int i = start; i < size; i++) {
            arrayInt[i] = random.nextInt();
        }
        return arrayInt;
    }

    public static int sumAllValues(int[] value) {
        int sum = 0;
        for (Integer i : value) {
            sum += i;
        }
        return sum;
    }

}
