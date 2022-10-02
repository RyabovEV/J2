package HW5;

public class ThreadHomework2 {
    public static void main(String[] args) {
        secondMethod();
    }

    public static void secondMethod() {
        int size = 10_000_000;
        float[] arr = new float[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 1.0f;
        }
        long startTime = System.currentTimeMillis();
        float[] leftHalf = new float[size / 2];
        float[] rightHalf = new float[size / 2];
        System.arraycopy(arr, 0, leftHalf, 0, size / 2);
        System.arraycopy(arr, size / 2, rightHalf, 0, size / 2);
        T t1 = new T(leftHalf);
        T t2 = new T(rightHalf);
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        float[] mergedArray = new float[size];
        System.arraycopy(t1.arr2, 0, mergedArray, 0, size / 2);
        System.arraycopy(t2.arr2, 0, mergedArray, size / 2, size / 2);
        System.out.println("Two thread time: " + (System.currentTimeMillis() - startTime) + " ms.");
    }

}

class A {
    float[] arrplus(float[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        return arr;
    }
}


class T extends Thread {
    final float[] arr;
    float[] arr2;
    final A a = new A();

    public T(float[] arr) {
        this.arr = arr;
    }

    @Override
    public void run() {
        this.arr2 = a.arrplus(arr);
    }
}
