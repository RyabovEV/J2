package HW2;

import java.util.Arrays;

public class MyArraySizeException extends Exception {

    private String massive[][];

    public MyArraySizeException(String[][] massive) {
        this.massive = massive;
    }

    @Override
    public String toString() {
        String message = "massive format exception{";
        for (int i = 0; i < massive.length; i++) {
            message += Arrays.toString(massive[i]);
        }
        message += '}';
        return message;
    }
}
