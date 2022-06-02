package HW2;

import java.util.Arrays;

public class MyArrayDataException extends NumberFormatException {
    private String string;
    private int i,j;

    public MyArrayDataException(String string, int i,int j) {
        this.string = string;
    }

    @Override
    public String toString() {
        return "massive data exception{ ячейка "+ (i+2) + "," + (j+2) + "[" + string + "]}";
    }
}
