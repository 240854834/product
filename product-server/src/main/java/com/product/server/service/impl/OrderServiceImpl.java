package com.product.server.service.impl;

import com.common.enums.order.OrderStatusEnum;
import com.common.enums.order.PayStatusEnum;
import com.common.model.CartInfo;
import com.common.model.OrderDetailInfo;
import com.common.model.OrderInfo;
import com.product.server.service.OrderService;
import com.product.server.service.ProductService;
import com.product.server.utils.KeyUtil;
import com.respository.OrderDetailRepository;
import com.respository.OrderMasterRepository;
import com.respository.dataObject.OrderDetailEntity;
import com.respository.dataObject.OrderMasterEntity;
import com.respository.dataObject.ProductInfoEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author jason
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public OrderInfo create(OrderInfo orderInfo) {
        String orderId = KeyUtil.genUniqueKey();
        //查询商品信息(调用商品服务)
        List<String> productIdList = orderInfo.getOrderDetailEntities().stream()
                .map(OrderDetailInfo::getProductId)
                .collect(Collectors.toList());
        List<ProductInfoEntity> productInfoEntities = productService.findByProductId(productIdList);

        //计算总价
        BigDecimal orderAmout = new BigDecimal(BigInteger.ZERO);
        for (OrderDetailInfo orderDetailInfo : orderInfo.getOrderDetailEntities()) {
            for (ProductInfoEntity productInfoEntity : productInfoEntities) {
                if (productInfoEntity.getProductId().equals(orderDetailInfo.getProductId())) {
                    //单价*数量
                    orderAmout = productInfoEntity.getProductPrice()
                            .multiply(new BigDecimal(orderDetailInfo.getProductQuantity()))
                            .add(orderAmout);

                    BeanUtils.copyProperties(productInfoEntity, orderDetailInfo);

                    OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
                    BeanUtils.copyProperties(orderDetailInfo, orderDetailEntity);
                    orderDetailEntity.setOrderId(orderId);
                    orderDetailEntity.setDetailId(KeyUtil.genUniqueKey());

                    orderDetailRepository.save(orderDetailEntity);
                }
            }
        }

        //扣库存(调用商品服务)
        List<CartInfo> cartInfos = orderInfo.getOrderDetailEntities().stream()
                .map(e -> new CartInfo(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartInfos);

        //订单入库
        OrderMasterEntity orderMasterEntity = OrderMasterEntity.builder()
                .orderId(orderId)
                .orderAmount(orderAmout)
                .buyerName(orderInfo.getBuyerName())
                .buyerOpenid(orderInfo.getBuyerOpenid())
                .buyerAddress(orderInfo.getBuyerAddress())
                .buyerPhone(orderInfo.getBuyerPhone())
                .orderStatus(OrderStatusEnum.NEW)
                .payStatus(PayStatusEnum.NEW)
                .build();
        orderMasterRepository.save(orderMasterEntity);
        return OrderMasterEntity.toOrderInfo(orderMasterEntity);
    }
}
