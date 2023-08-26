package com.fxTrade;

import java.util.ArrayList;

public class Trade {
    String customerName;
    String currencyPair;
    int amount;
    String getRate;
    static final int RATE = 66;
    static ArrayList<Trade> trades = new ArrayList<>();

    Trade(String customerName, String currencyPair, int amount, String getRate) {
        this.customerName = customerName;
        this.currencyPair = currencyPair;
        this.amount = amount;
        this.getRate = getRate;
    }
}