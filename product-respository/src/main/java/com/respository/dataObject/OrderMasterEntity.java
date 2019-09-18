package com.respository.dataObject;

import com.common.enums.order.OrderStatusEnum;
import com.common.enums.order.PayStatusEnum;
import com.common.model.OrderInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jason
 */
@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderMasterEntity implements Serializable {

    private static final long serialVersionUID = 3293347385534689815L;

    @Id
    private String orderId;

    @Column(name = "buyer_name")
    private String buyerName;

    @Column(name = "buyer_phone")
    private String buyerPhone;

    @Column(name = "buyer_address")
    private String buyerAddress;

    @Column(name = "buyer_openid")
    private String buyerOpenid;

    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    @Column(name = "order_status")
    private OrderStatusEnum orderStatus;

    @Column(name = "pay_status")
    private PayStatusEnum payStatus;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    public static OrderInfo toOrderInfo(OrderMasterEntity orderMasterEntity) {
        if (orderMasterEntity == null) {
            return null;
        }

        return OrderInfo.builder()
                .buyerPhone(orderMasterEntity.getBuyerPhone())
                .buyerOpenid(orderMasterEntity.getBuyerOpenid())
                .buyerAddress(orderMasterEntity.getBuyerAddress())
                .buyerName(orderMasterEntity.getBuyerName())
                .orderId(orderMasterEntity.getOrderId())
                .build();

    }
}
