package com.ernesto.cloud.azure.ecommerce.properties;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class AppConfigurationProperties {
    private Map<String,String> keys;
}
