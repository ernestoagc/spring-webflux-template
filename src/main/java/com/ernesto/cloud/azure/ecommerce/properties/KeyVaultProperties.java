package com.ernesto.cloud.azure.ecommerce.properties;

import com.ernesto.cloud.azure.ecommerce.model.KeyVaultSecretModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class KeyVaultProperties {
    private Map<String,String> secrets;
    private String endpoint;
    private String clientId;
    private String clientSecret;
    private String tenantId;
}

