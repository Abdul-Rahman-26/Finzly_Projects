package com.finzly.fxTrade.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finzly.fxTrade.dao.CurrencyPairDao;
import com.finzly.fxTrade.exception.CurrencyPairAlreadyExistsException;
import com.finzly.fxTrade.exception.CurrencyPairNotFoundException;
import com.finzly.fxTrade.model.CurrencyPair;

@Service
public class CurrencyPairService {

	private CurrencyPairDao currencyPairDao;
	private final Logger logger = LoggerFactory.getLogger(CurrencyPairService.class);

	@Autowired
	public CurrencyPairService(CurrencyPairDao currencyPairDao) {
		this.currencyPairDao = currencyPairDao;
	}

	public String addCurrencyPair(CurrencyPair currencyPair) {
		String cleanedCurrencyPair = currencyPair.getCurrencyPair().trim().toLowerCase();
		currencyPair.setCurrencyPair(cleanedCurrencyPair);
		if (currencyPairDao.getCurrencyPairByCurrency(cleanedCurrencyPair) != null) {
			logger.warn("Currency Pair is already exists");
			throw new CurrencyPairAlreadyExistsException("Currency Pair is already exists ");
		}

		if (currencyPair.getRate() <= 0) {
			return "Invalid rate.";
		}

		CurrencyPair cleanedPair = new CurrencyPair(cleanedCurrencyPair, currencyPair.getRate());

		String result = currencyPairDao.addCurrencyPair(cleanedPair);

		return result;
	}

	public double getRateByCurrencyPair(String currencyPair) {
		double rate = currencyPairDao.getRateByCurrencyPair(currencyPair);

		if (rate == 0) {
			logger.warn("Currency pair not found");
			throw new CurrencyPairNotFoundException("Currency pair not found: " + currencyPair);
		}

		return rate;
	}

	public String updateRateByCurrencyPair(CurrencyPair currencyPair) {
		double currentRate = currencyPairDao.getRateByCurrencyPair(currencyPair.getCurrencyPair());

		if (currentRate == 0) {
			logger.warn("Currency pair not found");
			throw new CurrencyPairNotFoundException("Currency pair not found: " + currencyPair.getCurrencyPair());
		}

		currencyPairDao.updateCurrencyPairRate(currencyPair);

		return "CurrencyPair rate updated for CurrencyPair: " + currencyPair.getCurrencyPair();
	}

	public List<CurrencyPair> getAllCurrencyPairs() {
		return currencyPairDao.getAllCurrencyPairs();
	}
}
