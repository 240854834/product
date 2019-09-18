package com.product.server.controller;

import com.common.model.OrderInfo;
import com.common.model.OrderRequest;
import com.product.server.service.OrderService;
import com.product.server.vo.ResultInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jason
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 查询商品信息(调用商品服务)
     * 计算总价
     * 扣库存(调用商品服务)
     * 订单入库
     */
    @PostMapping("/create")
    public ResultInfo create(@RequestBody @Valid OrderRequest request) {
        OrderInfo orderInfo = OrderInfo.from(request);
        OrderInfo result = orderService.create(orderInfo);

        Map<String, String> map = new HashMap<>(1);
        map.put("orderId", result.getOrderId());
        return ResultInfo.success(map);
    }
}
