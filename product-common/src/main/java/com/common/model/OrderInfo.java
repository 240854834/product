package com.common.model;

import com.common.enums.order.OrderStatusEnum;
import com.common.enums.order.PayStatusEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jason
 */
@Data
@Builder
public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1506986283617891239L;

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    private OrderStatusEnum orderStatus;

    private PayStatusEnum payStatus;

    private List<OrderDetailInfo> orderDetailEntities;

    public static OrderInfo from(OrderRequest orderRequest) {
        if (orderRequest == null) {
            return null;
        }

        OrderInfo dto = OrderInfo.builder()
                .buyerName(orderRequest.getName())
                .buyerAddress(orderRequest.getAddress())
                .buyerOpenid(orderRequest.getOpenid())
                .buyerPhone(orderRequest.getPhone())
                .build();

        if (orderRequest.getItems().size() > 0) {
            List<OrderRequest.Product> products = orderRequest.getItems();
            List<OrderDetailInfo> orderDetailEntities = products.stream().map(x -> {
                return OrderDetailInfo.builder()
                        .productId(x.getProductId())
                        .productQuantity(x.getProductQuantity())
                        .build();
            }).collect(Collectors.toList());
            dto.setOrderDetailEntities(orderDetailEntities);
        }

        return dto;
    }

}
