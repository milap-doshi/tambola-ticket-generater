package com.milap.tambolaticketgenerator.model;

import java.util.Arrays;

public class Ticket {
    private String id;
    private int[][] numbers = new int[3][9];

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int[][] getNumbers() {
        return numbers;
    }

    public void setNumbers(int[][] numbers) {
        this.numbers = numbers;
    }

    public static int getRowCount(int[][] numbers, int r) {
        int count = 0;
        for (int i = 0; i < 9; i++) {
            if (numbers[r][i] != 0) count++;
        }

        return count;
    }

    @Override
    public int hashCode() {
        int[] flattenNumbers = new int[15];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                if (numbers[i][j] != 0) {
                    flattenNumbers[index++] = numbers[i][j];
                }
            }
        }
        Arrays.sort(flattenNumbers);
        int hash = 13;
        for (int i = 0; i < flattenNumbers.length; i++) {
            hash = hash * 17 + flattenNumbers[i];
        }
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Ticket)) {
            return false;
        }
        Ticket ticket = (Ticket) obj;
        return ticket.hashCode() == this.hashCode();
    }
}
