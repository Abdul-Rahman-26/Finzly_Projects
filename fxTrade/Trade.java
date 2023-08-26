package com.fxTrade;

import java.util.ArrayList;

public class Trade {
    String customerName;
    String currencyPair;
    long amount;
    String getRate;
    static final int RATE = 66;
    static ArrayList<Trade> trades = new ArrayList<>();

    Trade(String customerName, String currencyPair, long amount2, String getRate) {
        this.customerName = customerName;
        this.currencyPair = currencyPair;
        this.amount = amount2;
        this.getRate = getRate;
    }
}