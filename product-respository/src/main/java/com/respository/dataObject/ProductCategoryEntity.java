package com.respository.dataObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @author jason
 */
@Data
@Entity
@Table(name = "product_category")
@AllArgsConstructor
@NoArgsConstructor
public class ProductCategoryEntity {

    @Id
    @GeneratedValue
    private Integer categoryId;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_type")
    private int categoryType;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}
