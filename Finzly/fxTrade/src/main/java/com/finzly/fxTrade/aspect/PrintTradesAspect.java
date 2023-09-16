package com.finzly.fxTrade.aspect;

import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PrintTradesAspect {

	private final Logger logger = LoggerFactory.getLogger(PrintTradesAspect.class);

	@Before("execution(* com.finzly.fxTrade.controller.BookTradeController.printTrades())")
	public void logPrintTradesStart() {
		logger.info("PrintTrades started ");
	}

	@After("execution(* com.finzly.fxTrade.controller.BookTradeController.printTrades())")
	public void logPrintTradesEnd() {
		logger.info("PrintTrades ended.");
	}
}
