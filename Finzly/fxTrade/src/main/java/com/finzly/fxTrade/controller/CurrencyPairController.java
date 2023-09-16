package com.finzly.fxTrade.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.finzly.fxTrade.model.CurrencyPair;
import com.finzly.fxTrade.service.CurrencyPairService;

@RestController
@RequestMapping("fxtrade")
public class CurrencyPairController {

	private CurrencyPairService currencyPairService;

	@Autowired
	public CurrencyPairController(CurrencyPairService currencyPairService) {
		this.currencyPairService = currencyPairService;
	}

	@PostMapping("addCurrencyPair")
	public String addCurrencyPair(@RequestBody CurrencyPair currencyPair) {
		return currencyPairService.addCurrencyPair(currencyPair);
	}

	@PostMapping("updateRate")
	public String updateRateByCurrencyPair(@RequestBody CurrencyPair currencyPair) {
		return currencyPairService.updateRateByCurrencyPair(currencyPair);
	}

	@GetMapping("getAllCurrencyPairs")
	public List<CurrencyPair> getAllCurrencyPairs() {
		return currencyPairService.getAllCurrencyPairs();
	}
}
