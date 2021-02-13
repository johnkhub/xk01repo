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

import com.xk01.model.OrderModel;
import com.xk01.service.OrderServ;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/xk01")
public class OrderRest {

  @Autowired
  OrderServ orderServ;
  
  @GetMapping("/orders")
  public ResponseEntity<List<OrderModel>> getAllOrders() {
    try {
      List<OrderModel> orders = new ArrayList<OrderModel>();
      orders=orderServ.getAllOrders();
      if (orders.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }
      return new ResponseEntity<>(orders,new HttpHeaders(), HttpStatus.OK);
    } catch (Exception e) {
      //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
      throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,e.getMessage(),e);
    }
  }
  

  @GetMapping("/orders/{oid}")
  public ResponseEntity<OrderModel> getOrderById(@PathVariable("oid") long oid) {
	OrderModel ord =orderServ.getOrderById(oid);
    if (ord!=null) {
      return new ResponseEntity<OrderModel>(ord,new HttpHeaders(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/orders/by/{stockid}")
  public ResponseEntity<List<OrderModel>>  getOrderByStockId(@PathVariable("stockid") long stockid) {
	  try {
	      List<OrderModel> orders = new ArrayList<OrderModel>();
	      orders =orderServ.findOrderByBidId(stockid);
	      if (orders.isEmpty()) {
	          return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
	        }
	        return new ResponseEntity<>(orders,new HttpHeaders(), HttpStatus.OK);
	      } catch (Exception e) {
	        //return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	        throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED,e.getMessage(),e);
	   }
  }
  
  @GetMapping("/orders/process/{oid}/{sid}")
  public ResponseEntity<Boolean>  processOrder(@PathVariable("oid") long oid,@PathVariable("sid") long sid) {
	  boolean b=orderServ.processOrder(oid, sid);
	  if(b==true)
		{
			return new ResponseEntity<>(true,new HttpHeaders(), HttpStatus.CREATED);
	  	}
	  	else {
	  	      return new ResponseEntity<>(false, HttpStatus.EXPECTATION_FAILED);
	  	}
  }
  
  
  
  @PostMapping("/orders")
  public ResponseEntity<OrderModel> createOrder(@RequestBody OrderModel inOrd) {
	OrderModel ord = orderServ.createOrder(inOrd);
	if(ord!=null)
	{
		return new ResponseEntity<>(ord,new HttpHeaders(), HttpStatus.CREATED);
  	}
  	else {
  	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
  	}
  }
	
  @PutMapping("/orders/{oId}")
  public ResponseEntity<OrderModel> createOrder(@PathVariable (value="oId") Long oId, @RequestBody OrderModel inOrd) {
	  try {
		  OrderModel ord= orderServ.updateOrder(oId,inOrd);
	    	if(ord!=null) {
	    	    return new ResponseEntity<>(ord,new HttpHeaders(), HttpStatus.CREATED);
	    	}
	    	else {
	    	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    	}
	    	
	    } catch (Exception e) {
	    	System.out.print("ER="+e.getMessage());
	      return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
	    }
  }
  
  @DeleteMapping("/orders/{oid}")
  public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("oid") Long oid) {
    try {
    	orderServ.deleteOrder(oid);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
    	 throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage(), e);
      //return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
  
  @DeleteMapping("/orders")
  public ResponseEntity<HttpStatus> deleteAllOrders() {
    try {
    	orderServ.deleteAllOrders();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
    	throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, e.getMessage(), e);
      //return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }
  }
  

}