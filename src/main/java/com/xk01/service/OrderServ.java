package com.xk01.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xk01.model.OrderModel;
import com.xk01.model.TradingModel;
import com.xk01.repository.OrderRepo;

//import com.sera.model.CustomerModel;
//import com.sera.model.ProductModel;
//import com.sera.repository.CustomerRepo;
//import com.sera.repository.ProductRepo;

@Service
public class OrderServ {

	@Autowired
	OrderRepo orderRepo;
	@Autowired
	TradingServ tradingServ;
	
	
	public List<OrderModel> getAllOrders(){
		List<OrderModel> ordList=orderRepo.findAll();
		if(ordList.size()>0) {
			return ordList;
		}
		else {
			return new ArrayList<OrderModel>();
		}
	}
	
	public OrderModel getOrderById(Long id) {
		Optional<OrderModel> ord=orderRepo.findById(id);
		if(ord.isPresent()) {
			return ord.get();
		}
		else {
			return null;/// throw
		}
	}
	
	public List<OrderModel> findOrderByBidId(long stockid){
		List<OrderModel> orders=orderRepo.findOrderByStockId(stockid);
		return orders;
	}
	public Boolean processOrder(Long oid,long stkid) {
		if(oid != null) {
			Optional<OrderModel> ord=orderRepo.findById(oid);
			if(ord.isPresent()) {
				OrderModel o1=ord.get();
				long o1q= ord.get().getQuantity();
				List<OrderModel> counterOrders=this.findOrderByBidId(stkid);
				if(!counterOrders.isEmpty()) {
					for(int x=0;x<counterOrders.size();x++) {
						System.out.println(o1.getBalance()+"--"+counterOrders.get(x).getQuantity());
						long sellQ=0;
						long buyQ=0;
						if(o1.getBalance()<=counterOrders.get(x).getQuantity()){
							sellQ=0;
							buyQ=o1.getBalance()+counterOrders.get(x).getBalance();
						}
						else {
							sellQ=o1.getBalance()-counterOrders.get(x).getQuantity();
							buyQ=counterOrders.get(x).getBalance()+counterOrders.get(x).getQuantity();
						}
						o1.setBalance(sellQ);
						counterOrders.get(x).setBalance(buyQ);
						OrderModel o2=this.updateOrder(o1.getOid(), o1);
						OrderModel co2=this.updateOrder(counterOrders.get(x).getOid(), counterOrders.get(x));
						TradingModel trad=new TradingModel();
						trad.setNote("Sell"+(x));
						trad.setPrice(o1.getPrice());
						trad.setBidid(o1.getOid());
						trad.setAskid(counterOrders.get(x).getOid());
						trad.setQuantity(sellQ);
						tradingServ.createTrade(trad);
						
						if(o1.getBalance() <=0) {	
							System.out.println(o2.getOid()+">>= "+o2.getBalance());
							break;
						}
						//System.out.println(counterOrders.get(x).getOid()+"--"+counterOrders.get(x).getStockid());
						//System.out.println();
					}
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}	
		}
		else {
			return false;
		}
	}
	
	public OrderModel updateOrder(Long oid,OrderModel inOrd) {
			if(oid != null) {
				Optional<OrderModel> ord=orderRepo.findById(oid);
				if(ord.isPresent()) {
					OrderModel newOrd=ord.get();
					newOrd.setQuantity(inOrd.getQuantity());
					newOrd.setPrice(inOrd.getPrice());
					newOrd.setBalance(inOrd.getBalance());
					newOrd.setType(inOrd.getType());
					return orderRepo.save(newOrd);
				}
				else {
					return null;
				}	
			}
			else {
				return null;
			}
	}
	
	public OrderModel createOrder(OrderModel inOrd) { 
		inOrd=orderRepo.save(inOrd);
		return inOrd;
	}
	
	public void deleteOrder(Long id) {
		Optional<OrderModel>ord=orderRepo.findById(id);
		if(ord.isPresent()) {
			orderRepo.deleteById(id);
		}
		else {
			//return null;
		}
	}
	
	public void deleteAllOrders() {
		orderRepo.deleteAll();
	}
}
