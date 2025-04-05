package com.ernesto.cloud.azure.ecommerce.dto;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class ProductDto {

    private String code;
    private String name;
    private BigDecimal price;

}
