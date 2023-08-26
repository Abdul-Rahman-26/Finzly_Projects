package com.fxTrade;

import java.util.Scanner;

public class Forex {

    private static Scanner scanner = new Scanner(System.in);
    private static boolean stop = false;
    private static BookTrade bookTrade = new BookTrade();

    public static void main(String[] args) {
        while (!stop) {
            showStandardOperations();
        }
    }

    private static void showStandardOperations() {
        System.out.println("| Book Trades  - 1\n| Print Trades - 2\n| Exit         - 3");
        System.out.println("Select Options");
        int n = scanner.nextInt();
        selectOption(n);
    }

    private static void selectOption(int n) {
        switch (n) {
            case 1:
                bookTrade.addData();
                break;
            case 2:
                printTrade();
                break;
            case 3:
                exit();
                break;
            default:
                System.out.println("Select Options between 1 to 3 Only");
        }
    }

    private static void exit() {
        System.out.println("Do you really want to exit (y/n)");
        char yesorno = scanner.next().charAt(0);
        if (yesorno == 'y' || yesorno == 'Y') {
            System.out.println("Bye - have a good day");
            stop = true;
        } else {
            showStandardOperations();
        }
    }

    private static void printTrade() {
        bookTrade.printTrades(Trade.trades);
    }
}
