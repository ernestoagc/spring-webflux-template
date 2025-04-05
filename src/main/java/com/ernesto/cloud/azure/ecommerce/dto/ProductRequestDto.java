package com.ernesto.cloud.azure.ecommerce.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class ProductRequestDto {
    private String code;
    private String name;
    private BigDecimal price;

}
