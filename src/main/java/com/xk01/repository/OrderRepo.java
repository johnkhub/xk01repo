package com.xk01.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xk01.model.OrderModel;



public interface OrderRepo extends JpaRepository<OrderModel,Long> {
	//@JsonIgnore
	@Query(value = "select o.oid,o.stockid,o.traderid,o.quantity,o.price,o.balance,o.type,o.ord_date,o.ord_time from tblorders o where o.stockid= ?1 ORDER BY o.traderid DESC,o.quantity DESC", nativeQuery = true)
	public List<OrderModel> findOrderByStockId(long stid);

}
