package com.wideka.club.trade.dao;

import java.util.List;

import com.wideka.club.api.trade.bo.Order;

/**
 * 
 * @author JiakunXu
 * 
 */
public interface IOrderDao {

	/**
	 * 
	 * @param order
	 * @return
	 */
	Long createOrder4Item(Order order);

	/**
	 * 
	 * @param order
	 * @return
	 */
	int createOrder4Cart(Order order);

	/**
	 * 
	 * @param order
	 * @return
	 */
	List<Order> getOrderList(Order order);

}
