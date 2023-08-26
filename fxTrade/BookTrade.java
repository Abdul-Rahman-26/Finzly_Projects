package com.fxTrade;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class BookTrade {
    private static Scanner scanner = new Scanner(System.in);
    private static final Pattern VALID_NAME_PATTERN = Pattern.compile("^[a-zA-Z\\s]+$");
    private static final int RATE = Trade.RATE;

    public void addData() {
        String customerName = getCustomerName();
        String currencyPair = getCurrencyPair();
        long amount = getValidAmount();
        String getRate = getRateOption();
        double tradesRate = calculateTradesRate(amount);

        displayTransactionSummary(customerName, currencyPair, amount, getRate, tradesRate);

        String bookOrCancel = getBookOrCancelChoice();
        if (bookOrCancel.equalsIgnoreCase("Book")) {
            Trade trade = createTrade(customerName, currencyPair, amount, getRate);
            Trade.trades.add(trade);
            displayTradeConfirmation(trade, tradesRate);
        } else if (bookOrCancel.equalsIgnoreCase("Cancel")) {
            System.out.println("Trade is Cancel");
        } else {
            System.out.println("Invalid choice. Please enter 'Book' or 'Cancel'.");
        }
    }

    private static String getBookOrCancelChoice() {
        scanner.nextLine();
        String choice;
        do {
            System.out.println("Book/Cancel this trade?");
            choice = scanner.nextLine();
            if (!choice.equalsIgnoreCase("Book") && !choice.equalsIgnoreCase("Cancel")) {
                System.out.println("Invalid input. Please enter 'Book' or 'Cancel'.");
            }
        } while (!choice.equalsIgnoreCase("Book") && !choice.equalsIgnoreCase("Cancel"));
        return choice;
    }

    private static String getCustomerName() {
        String customerName;
        do {
            System.out.println("Enter customer Name");
            customerName = scanner.nextLine();
            if (!isValidName(customerName)) {
                System.out.println("Invalid name. Please enter a valid customer name.");
            }
        } while (!isValidName(customerName));
        return customerName;
    }

    private static String getCurrencyPair() {
        String currencyPair = null;
        boolean validOptionSelected = false;

        while (!validOptionSelected) {
            System.out.println("Enter Currency Pair");
            System.out.println("| USRINR - 1");
            
            int optionPair = scanner.nextInt();
            switch (optionPair) {
                case 1:
                    currencyPair = "USRINR";
                    validOptionSelected = true;
                    break;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
        
        return currencyPair;
    }

    private static long getValidAmount() {
        boolean validAmountEntered = false;
        long amount = 0;

        do {
            System.out.println("Enter amount to transfer");

            if (scanner.hasNextInt()) {
                amount = scanner.nextInt();
                if (amount < 0) {
                    System.out.println("Enter a valid amount (greater than or equal to 0)");
                } else {
                    validAmountEntered = true;
                }
            } else {
                System.out.println("Invalid input. Please enter a valid numeric amount.");
                scanner.next(); 
            }

        } while (!validAmountEntered);

        return amount;
    }

    private static String getRateOption() {
        System.out.println("Do you want to get Rate");
        System.out.println("| Yes  - 1\n| No   - 2");
        int optionRate = scanner.nextInt();
        String getRate = null;
        switch (optionRate) {
            case 1:
                getRate = "Yes";
                break;
            case 2:
                getRate = "No";
                break;
            default:
                System.out.println("Select option 1 or 2");
        }
        return getRate;
    }

    private static double calculateTradesRate(long amount) {
        return RATE * amount;
    }

    private static void displayTransactionSummary(String customerName, String currencyPair,
                                                  long amount, String getRate, double tradesRate) {
        if (getRate.equals("Yes")) {
            System.out.println("You are transferring INR " + format(tradesRate) +
                    " to " + customerName + " .(Assuming that rate was " + format(RATE) + ")");
        }
    }

    private static Trade createTrade(String customerName, String currencyPair,
                                     long amount, String getRate) {
        return new Trade(customerName, currencyPair, amount, getRate);
    }

    private static void displayTradeConfirmation(Trade trade, double tradesRate) {
        NumberFormat numberFormat = NumberFormat.getInstance(new Locale("en", "IN"));
        System.out.println("Trade for " + trade.currencyPair + " has been booked with rate " +
                format(RATE) + " , The amount of Rs " + numberFormat.format(tradesRate) +
                " will be transferred in 2 working days to " + trade.customerName);
    }

    private static String format(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        return decimalFormat.format(value);
    }

    public void printTrades(ArrayList<Trade> trades) {
        System.out.println("| TradeNo\t CurrencyPair\tCustomerName\tAmount\tRate");
        for (int index = 0; index < trades.size(); index++) {
            Trade trade = trades.get(index);
            double tradesRate = RATE * trade.amount;
            System.out.println("|\t" + (index + 1) + "\t" + trade.currencyPair +
                    "\t" + trade.customerName + "\t" + "INR" + format(tradesRate) + "\t" + RATE);
        }
    }

    private static boolean isValidName(String name) {
        return VALID_NAME_PATTERN.matcher(name).matches();
    }
}
