package com.finzly.fxTrade.trades;

public class Trade {
	private String customerName;
	private String currencyPair;
    
    private double amount;
    
    private String getRate;
    private String choice;
    private double transferredAmount;
    public Trade() {
    	
    }

    public Trade(String customerName, String currencyPair, double amount, String getRate, String choice,double calculatedTradesRate) {
        this.customerName = customerName;
        this.currencyPair = currencyPair;
        this.amount = amount;
        this.getRate = getRate;
        this.choice = choice;
    }
    
	public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setGetRate(String getRate) {
        this.getRate = getRate;
    }
    public String getChoice() {
		return choice;
	}
	public void setChoice(String choice) {
		this.choice = choice;
	}
    
	
    public String getGetRate() {
        return getRate;
    }

	public double getTransferredAmount() {
		return transferredAmount;
	}

	public void setTransferredAmount(double transferredAmount) {
		this.transferredAmount = transferredAmount;
	}

	@Override
	public String toString() {
		return "Trade [customerName=" + customerName + ", currencyPair=" + currencyPair + ", amount=" + amount
				+ ", getRate=" + getRate + ", choice=" + choice +"]";
	}
    
    

 }
