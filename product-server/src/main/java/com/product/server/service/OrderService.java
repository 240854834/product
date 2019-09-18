package com.product.server.service;


import com.common.model.OrderInfo;

/**
 * @author jason
 */
public interface OrderService {

    OrderInfo create(OrderInfo orderDTO);
}
