package com.ernesto.cloud.azure.ecommerce.properties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AzureServiceBusProperties {
    private String queue;
    private String namespace;
}
