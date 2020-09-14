package com.xk01.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.xk01.model.OrderModel;
import com.xk01.model.TradingModel;
import com.xk01.repository.OrderRepo;
import com.xk01.repository.TradingRepo;

//import com.sera.model.CustomerModel;
//import com.sera.model.OrderModel;
//import com.sera.model.ProductModel;
//import com.sera.repository.OrderRepo;
//import com.sera.repository.ProductRepo;

@Service
public class TradingServ {
	
	@Autowired
	TradingRepo tradingRepo;
	@Autowired
	OrderServ orderServ;
	///@Autowired
	///ProductService productService;
	
	public List<TradingModel> getAllTradings(){
		List<TradingModel> tradList=tradingRepo.findAll();
		if(tradList.size()>0) {
			return tradList;
		}
		else {
			return new ArrayList<TradingModel>();
		}
	}
	
	public TradingModel getTradingById(Long id) {
		Optional<TradingModel> trad=tradingRepo.findById(id);
		if(trad.isPresent()) {
			return trad.get();
		}
		else {
			return null;/// throw
		}
	}
	/*
	public TradingModel updateTrading(Long tid,TradingModel inTrad) {
			if(tid != null) {
				Optional<TradingModel> trad=tradingRepo.findById(tid);
				if(trad.isPresent()) {
					TradingModel newTrad=trad.get();
					newTrad.setNote(inTrad.getNote());
					newTrad.setOrderbid(inTrad.getOrderbid());
					newTrad.setOrderask(inTrad.getOrderask());
					return newTrad;
				}
				else {
					return null;
				}	
			}
			else {
				return null;
			}
	}
	*/
	public TradingModel createTrade(TradingModel inTrad) { 
		inTrad=tradingRepo.save(inTrad);
		return inTrad;
	}
	public TradingModel createTrading(Long bidId,Long askId,TradingModel inTrad) {
		OrderModel bid=orderServ.getOrderById(bidId);
		OrderModel ask=orderServ.getOrderById(askId);
    	if(bid!=null && ask!=null) {
    		//inTrad.setOrderbid(bid);
    		//inTrad.setOrderask(ask);
    		inTrad=tradingRepo.save(inTrad);
    		return inTrad;
    	}
    	else {
    		return null;
    	}
	}
	
	public void deleteTrading(Long id) {//throws
		Optional<TradingModel>trad=tradingRepo.findById(id);
		if(trad.isPresent()) {
			tradingRepo.deleteById(id);
		}
		else {
			//return null;//thows
		}
	}
	
	public void deleteAlltradings() {
		tradingRepo.deleteAll();
	}
	
}