package com.ernesto.cloud.azure.ecommerce.properties;

import com.ernesto.cloud.azure.ecommerce.model.KeyVaultSecretModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KeyVaultProperties {
    private KeyVaultSecretModel sendgrid;
    private String endpoint;
}

