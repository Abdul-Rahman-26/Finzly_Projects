package com.finzly.fxTrade.service;

import org.springframework.stereotype.Service;
import com.finzly.fxTrade.trades.Trade;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class TradeService {

    private final List<Trade> bookedTrades = new ArrayList<>();
    private static final double RATE = 66;
    private static double tradesRates;

    public String processTrade(Trade trade) {
        if (!isValidTrade(trade)) {
            return "Invalid trade. Please check customer name, currency pair, and amount.";
        }

        tradesRates = calculateTradesRate(trade.getAmount());
        String transactionSummary = displayTransactionSummary(trade);
        
        if ("Book".equalsIgnoreCase(trade.getChoice())) {
            bookedTrades.add(trade);
            trade.setTransferredAmount(tradesRates);
            String confirmation = displayTradeConfirmation(trade, tradesRates);
            return transactionSummary + (confirmation != null ? "\n" + confirmation : "");
        } else if ("Cancel".equalsIgnoreCase(trade.getChoice())) {
            return transactionSummary +"\nTrade is Cancel";
        } else {
            return "Invalid choice. Please enter 'Book' or 'Cancel'.";
        }
    }

    public static double calculateTradesRate(double amount) {
        return RATE * amount;
    }

    private static String displayTransactionSummary(Trade trade) {
        if (trade.getGetRate().equalsIgnoreCase("Yes")) {
            return "You are transferring INR " + format(tradesRates) + " to " + trade.getCustomerName();
        } else {
            return ""; 
        }
    }

    private String displayTradeConfirmation(Trade trade, double tradesRates) {
        return "Trade for " + trade.getCurrencyPair() + " has been booked with rate " + format(RATE)
                + " , The amount of Rs " + format(tradesRates) + " will be transferred in 2 working days to "
                + trade.getCustomerName();
    }

    private static String format(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        return decimalFormat.format(value);
    }

    public List<Trade> getBookedTrades() {
        return bookedTrades;
    }

    public static double getRate() {
        return RATE;
    }

    private boolean isValidTrade(Trade trade) {
        return isValidCustomerName(trade.getCustomerName()) &&
               isValidCurrencyPair(trade.getCurrencyPair()) &&
               isValidAmount(trade.getAmount());
    }

    private boolean isValidCustomerName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return false; 
        }
        return name.matches("^[a-zA-Z\\s]*$");
    }

    private boolean isValidCurrencyPair(String currencyPair) {
        return currencyPair != null && currencyPair.trim().equalsIgnoreCase("usdinr");
    }

    private boolean isValidAmount(double amount) {
        return amount > 0;
    }
}
