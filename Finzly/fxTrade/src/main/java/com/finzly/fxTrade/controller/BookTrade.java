package com.finzly.fxTrade.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.finzly.fxTrade.service.TradeService;
import com.finzly.fxTrade.trades.Trade;

import java.util.List;

@RestController
@RequestMapping("fxtrade")
public class BookTrade {

    private final TradeService tradeService;

    @Autowired
    public BookTrade(TradeService tradeService) {
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
