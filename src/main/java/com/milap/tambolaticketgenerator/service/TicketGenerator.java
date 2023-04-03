package com.milap.tambolaticketgenerator.service;

import com.milap.tambolaticketgenerator.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class TicketGenerator {
    private static final int[] EXCEPT_ZERO = new int[]{1, 2};
    private static final int[] EXCEPT_ONE = new int[]{0, 2};
    private static final int[] EXCEPT_TWO = new int[]{0, 1};
    private static Random random = new Random();

    private SessionValidationService sessionValidator = null;

    @Autowired
    public TicketGenerator(SessionValidationService sessionValidator) {
        this.sessionValidator = sessionValidator;
    }

    public Ticket getNewTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(UUID.randomUUID().toString());
        int[][] numbersArray = ticket.getNumbers();
        /*Initializes a number in each columns*/
        prepareTemplate(numbersArray);
        /*Replace place holders to actual numbers on ticket*/
        fillNumbersToTicket(numbersArray);
        return ticket;
    }

    private void fillNumbersToTicket(int[][] numbersArray) {
        for (int i = 0; i < 9; i++) {
            int min = i * 10;
            int max = min + 9;
            if (i == 0) {
                min++;
            }
            if (i == 8) {
                max++;
            }

            int[] numbers = random.ints(min, max + 1).distinct().limit(3).sorted().toArray();
            int numIndex = 0;
            for (int j = 0; j < 3; j++) {
                if (numbersArray[j][i] == 1) {
                    numbersArray[j][i] = numbers[numIndex++];
                }
            }
        }
    }

    /**
     * @param array
     */
    private void prepareTemplate(int[][] array) {
        initializeEachColumnsForTemplate(array);
        initializeMissingRows(array);
    }

    private void initializeMissingRows(int[][] array) {
        for (int i = 0; i < 3; i++) {
            int count = Ticket.getRowCount(array, i);
            if (count < 5) {
                int remaining = 5 - count;
                while (remaining > 0) {
                    int rowIndex = random.nextInt(9);
                    if (array[i][rowIndex] != 1) {
                        array[i][rowIndex] = 1;
                        remaining--;
                    }
                }
            }
        }
    }

    private void initializeEachColumnsForTemplate(int[][] array) {
        int lastPos = -1;
        for (int i = 0; i < 9; i++) {
            int pos = -1;
            switch (lastPos) {
                case -1:
                    pos = random.nextInt(2);
                    break;
                case 0:
                    pos = EXCEPT_ZERO[random.nextInt(EXCEPT_ZERO.length)];
                    break;
                case 1:
                    pos = EXCEPT_ONE[random.nextInt(EXCEPT_ONE.length)];
                    break;
                case 2:
                    pos = EXCEPT_TWO[random.nextInt(EXCEPT_TWO.length)];
                    break;
            }
            array[pos][i] = 1;
            lastPos = pos;
        }
    }

    /*private static void printTicket(int[][] ticketArray) {
        for (int[] row :
                ticketArray) {
            for (int number :
                    row) {
                if (number == 0) {
                    System.out.print(" " + "\t");
                } else {
                    System.out.print(number + "\t");
                }
                System.out.print("|");
            }
            System.out.println();
        }
        System.out.println("\n\n\n *********************");
    }*/
}
