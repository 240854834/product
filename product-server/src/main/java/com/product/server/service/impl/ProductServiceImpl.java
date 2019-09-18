package com.product.server.service.impl;

import com.common.enums.product.ProductStatusEnum;
import com.common.model.CartInfo;
import com.product.server.exception.ProductException;
import com.product.server.service.ProductService;
import com.respository.ProductInfoRepository;
import com.respository.dataObject.ProductInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @author jason
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public List<ProductInfoEntity> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public List<ProductInfoEntity> findByProductId(List<String> productIds) {
        return productInfoRepository.findByProductIdIn(productIds);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void decreaseStock(List<CartInfo> cartInfos) {
        for (CartInfo cartInfo : cartInfos) {
            Optional<ProductInfoEntity> productInfoOptional = productInfoRepository.findById(cartInfo.getProductId());
            //判断商品是否存在
            if (!productInfoOptional.isPresent()) {
                throw new ProductException("商品不存在");
            }

            ProductInfoEntity productInfoEntity = productInfoOptional.get();
            //判断是否足够
            Integer result = productInfoEntity.getProductStock() - cartInfo.getProductQuantity();
            if (result < 0) {
                throw new ProductException("库存有误");
            }
            productInfoEntity.setProductStock(result);
            productInfoRepository.save(productInfoEntity);
        }
    }
}
