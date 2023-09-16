package com.finzly.fxTrade.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.finzly.fxTrade.dao.TradeDao;
import com.finzly.fxTrade.exception.EmptyTradesException;
import com.finzly.fxTrade.model.Trade;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class TradeService {

	private final TradeDao tradeDao;
	private final CurrencyPairService currencyPairService;

	@Autowired
	public TradeService(TradeDao tradeDao, CurrencyPairService currencyPairService) {
		this.tradeDao = tradeDao;
		this.currencyPairService = currencyPairService;
	}

	public String processTrade(Trade trade) {
		String currencyPair = removeNonAlphabetCharacters(trade.getCurrencyPair());
		if (currencyPair.length() != 6) {
			return ("Currency pair must be 6 characters long.");
		}
		trade.setCurrencyPair(currencyPair);
		if (!isValidTrade(trade)) {
			return "Invalid trade. Please check customer name, currency pair, and amount.";
		}

		double rate = currencyPairService.getRateByCurrencyPair(currencyPair);
		double tradeRate = calculateTradeRate(trade.getAmount(), rate);
		trade.setTransferredAmount(tradeRate);
		String transactionSummary = displayTransactionSummary(trade);

		if ("Book".equalsIgnoreCase(trade.getChoice())) {
			trade.setTransferredAmount(tradeRate);
			tradeDao.addTrade(trade);
			String confirmation = displayTradeConfirmation(trade, tradeRate, rate);
			return transactionSummary + (confirmation != null ? "\n" + confirmation : "");
		} else if ("Cancel".equalsIgnoreCase(trade.getChoice())) {
			return transactionSummary + "\nTrade is Cancel";
		} else {
			return "Invalid choice. Please enter 'Book' or 'Cancel'.";
		}
	}

	public double calculateTradeRate(double amount, double rate) {
		return rate * amount;
	}

	private String displayTransactionSummary(Trade trade) {
		if ("Yes".equalsIgnoreCase(trade.getGetRate())) {
			String isoCode = trade.getCurrencyPair().substring(3);
			return "You are transferring " + isoCode.toUpperCase() + " " + format(trade.getTransferredAmount()) + " to "
					+ trade.getCustomerName();
		} else {
			return "";
		}
	}

	private String displayTradeConfirmation(Trade trade, double tradeRate, double rate) {
		return "Trade for " + trade.getCurrencyPair().toUpperCase() + " has been booked with rate " + format(rate)
				+ ", The amount of Rs " + format(tradeRate) + " will be transferred in 2 working days to "
				+ trade.getCustomerName();
	}

	private static String removeNonAlphabetCharacters(String input) {
		return input.replaceAll("[^a-zA-Z]", "");
	}

	private String format(double value) {
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		return decimalFormat.format(value);
	}

	private boolean isValidTrade(Trade trade) {
		return isValidCustomerName(trade.getCustomerName()) && isValidCurrencyPair(trade.getCurrencyPair())
				&& isValidAmount(trade.getAmount());
	}

	private boolean isValidCustomerName(String name) {
		name = name.trim();
		return name != null && !name.trim().isEmpty() && name.matches("^[a-zA-Z\\s]*$");
	}

	private boolean isValidCurrencyPair(String currencyPair) {
		currencyPair = currencyPair.trim();
		return currencyPair != null && !currencyPair.trim().isEmpty() && currencyPair.matches("^[a-zA-Z\\s]*$");
	}

	private boolean isValidAmount(double amount) {
		return amount > 0;
	}

	public List<Trade> getBookedTrades() {
		List<Trade> trades = tradeDao.getAllTrades();
		if (trades == null || trades.isEmpty()) {
			throw new EmptyTradesException("No trades found.");
		}

		return trades;
	}

}
