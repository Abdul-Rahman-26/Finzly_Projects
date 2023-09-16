package com.finzly.fxTrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.finzly.fxTrade.model.Trade;
import com.finzly.fxTrade.service.TradeService;
import java.util.List;

@RestController
@RequestMapping("fxtrade")
public class BookTradeController {

	private final TradeService tradeService;

	@Autowired
	public BookTradeController(TradeService tradeService) {
		this.tradeService = tradeService;
	}

	@PostMapping("bookTrade")
	public String addTrades(@RequestBody Trade trade) {
		return tradeService.processTrade(trade);
	}

	@GetMapping("trades")
	public List<Trade> printTrades() {
		return tradeService.getBookedTrades();
	}

}
