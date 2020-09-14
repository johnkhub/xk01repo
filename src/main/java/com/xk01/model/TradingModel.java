package com.xk01.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "tbltrading")
public class TradingModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long tid;

	
/*
	@JsonProperty("bidid")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "bidid")
    @JsonIdentityReference(alwaysAsId = true)
	@ManyToOne()//fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "oid", nullable = false)
	private OrderModel orderbid;
	
	@JsonProperty("askid")
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "askid")
    @JsonIdentityReference(alwaysAsId = true)
	@ManyToOne()//fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "oid", nullable = false)
	private OrderModel orderask;
*/
	@Column(name = "note")
	private String note;
	
	@Column(name = "bidid")
	private long bidid;
	
	@Column(name = "askid")
	private long askid;
	
	
	@Column(name = "quantity")
	private long quantity;
	
	@Column(name = "price")
	private double price;

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	/*
	public OrderModel getOrderbid() {
		return orderbid;
	}

	public void setOrderbid(OrderModel orderbid) {
		this.orderbid = orderbid;
	}

	public OrderModel getOrderask() {
		return orderask;
	}

	public void setOrderask(OrderModel orderask) {
		this.orderask = orderask;
	}
*/
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public TradingModel() {}
	
	public TradingModel(long bidid,long askid,String note, long quantity, double price) {
		super();
		this.bidid = bidid;
		this.askid = askid;
		this.note = note;
		this.quantity = quantity;
		this.price = price;
	}

	public long getBidid() {
		return bidid;
	}

	public void setBidid(long bidid) {
		this.bidid = bidid;
	}

	public long getAskid() {
		return askid;
	}

	public void setAskid(long askid) {
		this.askid = askid;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
