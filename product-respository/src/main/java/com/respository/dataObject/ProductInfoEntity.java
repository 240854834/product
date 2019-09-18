package com.respository.dataObject;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author jason
 */
@Data
@Entity
@Builder
@Table(name = "Product_info")
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfoEntity {

    @Id
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "product_stock")
    private int productStock;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_icon")
    private String productIcon;

    @Column(name = "product_status")
    private int productStatus;

    @Column(name = "category_type")
    private int categoryType;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
