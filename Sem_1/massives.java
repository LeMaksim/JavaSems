package Sem_1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

// 1.	Выбросить случайное целое число и сохранить в i
// 2.	Посчитать и сохранить в n номер старшего значащего бита выпавшего числа
// 3.	Найти все кратные n числа большие i и сохранить в массив m1
// 4.	Найти все некратные n числа меньшие i и сохранить в массив m2
// 5.	Сохранить оба массива в файлы с именами m1 и m2 соответственно.

// Пункты реализовать в методе main
// *Пункты реализовать в разных методах
// **Реализовать один из пунктов рекурсией

public class massives {
    public static int i;
    public static int n;
    public static int minimum = Integer.MIN_VALUE / 100000;
    public static int maximum = Integer.MAX_VALUE / 10000;

    public static void main(String[] args) {
        // 1. Random number from -21474 to  21474 placed in i
        i = random();
        // 2. Most significant bit of i , or it's binary representation placed in n
        n = msb(i, 0);
        // 3. Every numbers that are multipule of n and greater than i and to maximum are placed in m1.txt
        Integer[] m1 = above();
        // 4. Every not multiple numbers of n from minimum to i placed in m2.txt
        Integer[] m2 = below();
        // 5. Each array saved in their name-relevant files  
        zapisVFile(m1, "m1.txt");
        zapisVFile(m2, "m2.txt");
    }

    public static int random() {
        int min = Integer.MIN_VALUE / 100000;
        int max = Integer.MAX_VALUE / 100000;
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public static int msb(int number, int result) {
        if (number < 0)
            number *= -1;
        if (number == 1)
            return result;
        else
            return msb(number >> 1, ++result);
    }

    public static Integer[] above() {
        ArrayList<Integer> sheet = new ArrayList<>();
        int index = 0;
        for (int j = i; j < maximum + 1; j++) {
            if (j % n == 0) {
                sheet.add(index, j);
                index++;
            }
        }
        Integer[] result = new Integer[sheet.size()];
        sheet.toArray(result);
        return result;
    }

    public static Integer[] below() {
        ArrayList<Integer> sheet = new ArrayList<>();
        int index = 0;
        for (int j = i; j > minimum; j--) {
            if (j % n != 0) {
                sheet.add(index, j);
                index++;
            }
        }
        Integer[] result = new Integer[sheet.size()];
        sheet.toArray(result);
        return result;
    }

    public static void zapisVFile(Integer[] array, String name) {
        String absoluteFilePath = name;
        File file = new File(absoluteFilePath);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(Arrays.toString(array));
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
