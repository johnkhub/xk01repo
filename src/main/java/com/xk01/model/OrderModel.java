package com.xk01.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tblorders")

public class OrderModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long oid;

	@Column(name = "stockid")
	private long stockid;
	
	@Column(name = "traderid")
	private long traderid;
	
	public OrderModel() {}
	
	public OrderModel(long stockid, long traderid, long quantity, double price,Long balance,String type,LocalDate odate,LocalTime otime) {
		super();
		this.stockid = stockid;
		this.traderid = traderid;
		this.quantity = quantity;
		this.price = price;
		this.balance=balance;
		this.type=type;
		this.ord_date= odate;
		this.ord_time= otime;
	}

	public long getOid() {
		return oid;
	}

	public void setOid(long oid) {
		this.oid = oid;
	}

	public long getStockid() {
		return stockid;
	}

	public void setStockid(long stockid) {
		this.stockid = stockid;
	}

	public long getTraderid() {
		return traderid;
	}

	public void setTraderid(long traderid) {
		this.traderid = traderid;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
/*
	public List<TradingModel> getTrading() {
		return trading;
	}

	public void setTrading(List<TradingModel> trading) {
		this.trading = trading;
	}
*/
	@Column(name = "quantity")
	private long quantity;
	
	@Column(name = "price")
	private double price;
	@Column(name = "balance")
	private Long balance;
	@Column(name = "type")
	private String type;
	@Column(name = "ord_date")
	private LocalDate ord_date;
	@Column(name = "ord_time")
	private LocalTime ord_time;
/*
	@OneToMany(mappedBy = "orderbid",fetch = FetchType.LAZY)
	private List<TradingModel> trading;
*/

	public LocalDate getOrd_date() {
		return ord_date;
	}

	public void setOrd_date(LocalDate ord_date) {
		this.ord_date = ord_date;
	}

	public LocalTime getOrd_time() {
		return ord_time;
	}

	public void setOrd_time(LocalTime ord_time) {
		this.ord_time = ord_time;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}
}
