package com.xk01.controller;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.xk01.model.TradingModel;
import com.xk01.service.OrderServ;
import com.xk01.service.TradingServ;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/xk01")
public class TradingRest {

  @Autowired
  TradingServ tradingServ;
  @Autowired
  OrderServ orderServ;
  
  @GetMapping("/trading")
  public ResponseEntity<List<TradingModel>> getAllTrades() {
    try {
      List<TradingModel> trades = new ArrayList<TradingModel>();
      trades=tradingServ.getAllTradings();
      if (trades.isEmpty()) {
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(trades,new HttpHeaders(), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/trading/{tid}")
  public ResponseEntity<TradingModel> getTradeById(@PathVariable("tid") long tid) {
	TradingModel trad =tradingServ.getTradingById(tid);
    if (trad!=null) {
      return new ResponseEntity<TradingModel>(trad,new HttpHeaders(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/trading/{bidId}/{askId}")
  public ResponseEntity<TradingModel> createTrade(@PathVariable (value="bidId") Long bidId, @PathVariable (value="askId") Long askId, @RequestBody TradingModel inTrad) {
	try {
		TradingModel trad = tradingServ.createTrading(bidId,askId,inTrad);
		if(trad!=null)
		{
			return new ResponseEntity<>(trad,new HttpHeaders(), HttpStatus.CREATED);
	  	}
	  	else {
	  		throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,"Null Bid Id or Ask Id",null);
	  	}
    	
    } catch (Exception e) {
    	//return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
  	    throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,e.getMessage(), e);
    }
  }
	/*
  @PutMapping("/trading/{tId}")
  public ResponseEntity<TradingModel> updateTrade(@PathVariable (value="tId") Long tId, @RequestBody TradingModel inTrad) {
	  try {
		  TradingModel trad= tradingServ.updateTrading(tId,inTrad);
	    	if(trad!=null) {
	    	    return new ResponseEntity<>(trad,new HttpHeaders(), HttpStatus.CREATED);
	    	}
	    	else {
	    	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    	}
	    	
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
  }
*/  
  
  
  @DeleteMapping("/trading/{tid}")
  public ResponseEntity<HttpStatus> deleteTrade(@PathVariable("tid") Long tid) {
    try {
      tradingServ.deleteTrading(tid);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
    	 throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(), e);
      //return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
  
  @DeleteMapping("/trading")
  public ResponseEntity<HttpStatus> deleteAllTrades() {
    try {
    	tradingServ.deleteAlltradings();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
    	throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, e.getMessage(), e);
      //return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
  
}