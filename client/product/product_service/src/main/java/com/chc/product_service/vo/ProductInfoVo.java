package com.chc.product_service.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author chc
 * @create 2019-01-04 17:19
 **/
@Data
public class ProductInfoVo {

    @JsonProperty("id")
    private String productId;

    @JsonProperty("name")
    private String productName;

    @JsonProperty("price")
    private BigDecimal productPrice;

    /** 描述 */
    @JsonProperty("description")
    private String productDescription;

    /** 小图 */
    @JsonProperty("icon")
    private String productIcon;
}
