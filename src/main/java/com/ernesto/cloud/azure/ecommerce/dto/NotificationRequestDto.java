package com.ernesto.cloud.azure.ecommerce.dto;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class NotificationRequestDto {
    private String code;
    private String name;
}
