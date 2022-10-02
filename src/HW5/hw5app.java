package HW5;

import java.util.Arrays;

/**
 * Необходимо написать два метода, которые делают следующее:
 * 1) Создают одномерный длинный массив, например:
 * static final int SIZE = 10_000_000;
 * static final int HALF = size / 2;
 * float[] arr = new float[size];
 * 2) Заполняют этот массив единицами.
 * 3) Засекают время выполнения: long a = System.currentTimeMillis().
 * 4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
 * Math.cos(0.4f + i / 2));
 * 5) Проверяется время окончания метода System.currentTimeMillis().
 * 6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a).
 * © geekbrains.ru 22
 * Код первого метода может быть вот таким:
 * public class ThreadHomework {
 * public static void main(String[] args) {
 * firstMethod();
 * }
 * public static void firstMethod() {
 * int size = 10_000_000;
 * float[] arr = new float[size];
 * for (int i = 0; i < arr.length; i++) {
 * arr[i] = 1.0f;
 * }
 * long startTime = System.currentTimeMillis();
 * for (int i = 0; i < arr.length; i++) {
 * arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i
 * / 5) * Math.cos(0.4f + i / 2));
 * }
 * System.out.println("One thread time: " + (System.currentTimeMillis() -
 * startTime) + " ms.");
 * }
 * }
 * Отличие первого метода от второго:
 * ● Первый просто бежит по массиву и вычисляет значения (это видно из кода выше).
 * ● Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и
 * потом склеивает эти массивы обратно в один.
 * Код второго метода может быть примерно таким:
 * public class ThreadHomework {
 * public static void main(String[] args) {
 * secondMethod();
 * }
 * public static void secondMethod() {
 * int size = 10_000_000;
 * float[] arr = new float[size];
 * for (int i = 0; i < arr.length; i++) {
 * arr[i] = 1.0f;
 * }
 * long startTime = System.currentTimeMillis();
 * // Создаем два массива для левой и правой части исходного
 * // Копируем в них значения из большого массива
 * // Запускает два потока и параллельно просчитываем каждый малый массив
 * // ...
 * // Склеиваем малые массивы обратно в один большой
 * // ...
 * System.out.println("Two thread time: " + (System.currentTimeMillis() -
 * startTime) + " ms.");
 * }
 * }
 * © geekbrains.ru 23
 * Пример деления одного массива на два:
 * ● System.arraycopy(arr, 0, a1, 0, h);
 * ● System.arraycopy(arr, h, a2, 0, h).
 * Пример обратной склейки:
 * ● System.arraycopy(a1, 0, arr, 0, h);
 * ● System.arraycopy(a2, 0, arr, h, h).
 * Пример кода для разделения массива на два и обратной склейки.
 * public static void splitAndMergeExample() {
 * int[] initialArray = {1, 2, 3, 4, 5, 6};
 * System.out.println(Arrays.toString(initialArray));
 * int[] leftHalf = new int[3];
 * int[] rightHalf = new int[3];
 * System.arraycopy(initialArray, 0, leftHalf, 0, 3);
 * System.arraycopy(initialArray, 3, rightHalf, 0, 3);
 * System.out.println(Arrays.toString(leftHalf));
 * System.out.println(Arrays.toString(rightHalf));
 * int[] mergedArray = new int[6];
 * System.arraycopy(leftHalf, 0, mergedArray, 0, 3);
 * System.arraycopy(rightHalf, 0, mergedArray, 3, 3);
 * System.out.println(Arrays.toString(mergedArray));
 * }
 * Примечание:
 * System.arraycopy() — копирует данные из одного массива в другой:
 * System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника,
 * массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек
 * копируем)
 * По замерам времени:
 * Для первого метода надо считать время только на цикл расчета:
 * for (int i = 0; i < size; i++) {
 * arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
 * Math.cos(0.4f + i / 2));
 * }
 * Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и
 * склейки.
 */

public class hw5app {
    public static void main(String[] args) {
        final Thread r = new Thread(() -> {
            final Thread thread = Thread.currentThread();
            thread.setName("MyThread");
            System.out.println("Привет из потока " + thread.getName());
            System.out.println("R.getStackTrace() = " + Arrays.toString(thread.getStackTrace()));
        });
        r.start();
        //final T t = new T();
        //t.start();
        printThreadInfo();

    }

    private static void printThreadInfo() {
        final Thread currentThread = Thread.currentThread();
        System.out.println("currentThread.getName() = " + currentThread.getName());
        System.out.println("currentThread.getStackTrace() = " + Arrays.toString(currentThread.getStackTrace()));
    }

    static class T extends Thread {
        @Override
        public void run() {
            System.out.println("Привет из потока " + Thread.currentThread().getName());
            System.out.println("T.getStackTrace() = " + Arrays.toString(Thread.currentThread().getStackTrace()));
        }
    }
}

class R implements Runnable {

    @Override
    public void run() {
        System.out.println("Привет из потока " + Thread.currentThread().getName());
        System.out.println("T.getStackTrace() = " + Arrays.toString(Thread.currentThread().getStackTrace()));
    }
}
