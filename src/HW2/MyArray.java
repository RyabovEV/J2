package HW2;

public class MyArray {
    private String massive[][];
    private int sum;
    private final String arrayIntForSum[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    private boolean calculationSum = true;

    public void showSum() {
        System.out.println(calculationSum ? "Сумма значений массива: " + sum : "массив заполнен неверно");
    }

    public void setMassive(String[][] massive) throws MyArraySizeException {
        if (massive.length == 4) {
            for (int i = 0; i < 4; i++) {
                if (massive[i].length != 4) {
                    calculationSum = false;
                    throw new MyArraySizeException(massive);
                }
            }
            this.massive = massive;
        } else {
            calculationSum = false;
            throw new MyArraySizeException(massive);
        }
        try {
            arraySum();
        } catch (MyArrayDataException e) {
            e.printStackTrace();
        }
    }

    void arraySum() {
        for (int i = 0; i < massive.length; i++) {
            for (int j = 0; j < massive[i].length; j++) {
                if (splitTestInt(massive[i][j], i, j)) sum += Integer.parseInt(massive[i][j]);
            }
        }
    }

    boolean splitTestInt(String a, int b, int c) {
        String[] words = a.split("");
        for (String word : words) {
            for (int i = 0; i < arrayIntForSum.length; i++) {
                if (word.equals(arrayIntForSum[i])) {
                    break;
                }
                if (i == arrayIntForSum.length - 1) {
                    calculationSum = false;
                    throw new MyArrayDataException(a, b, c);
                }

            }
        }
        return true;
    }


}
