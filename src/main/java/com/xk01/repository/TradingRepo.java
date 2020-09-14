package com.xk01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.xk01.model.TradingModel;

public interface TradingRepo extends JpaRepository<TradingModel,Long> {

}
