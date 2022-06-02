package HW2;

import java.util.Arrays;

public class MyArrayDataException extends NumberFormatException {
    private String string;
    private int i,j;

    public MyArrayDataException(String string, int i,int j) {
        this.string = string;
        this.i = i;
        this.i = i;
    }

    @Override
    public String toString() {
        return "massive data exception{ Значение " + string + " Строка " + (i + 1)  + " Столбец " + (j + 1) + "}";
    }
}
