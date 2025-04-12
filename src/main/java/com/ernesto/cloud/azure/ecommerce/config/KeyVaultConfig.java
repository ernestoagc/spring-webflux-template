package com.ernesto.cloud.azure.ecommerce.config;

import com.azure.identity.DefaultAzureCredentialBuilder;

import com.azure.security.keyvault.secrets.SecretAsyncClient;
import com.azure.security.keyvault.secrets.SecretClient;
import com.azure.security.keyvault.secrets.SecretClientBuilder;
import com.ernesto.cloud.azure.ecommerce.properties.KeyVaultProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

@Configuration
public class KeyVaultConfig {

    @Bean
    @ConfigurationProperties(prefix = "application.ernestotech.key-vault")
    public KeyVaultProperties keyVaultProperties(){
        return new KeyVaultProperties();
    }


    @Bean(name = "secretClient")
    public SecretClient getSecretClient(KeyVaultProperties keyVaultProperties){
        return new SecretClientBuilder()
                .vaultUrl(keyVaultProperties.getEndpoint())
                .credential(new DefaultAzureCredentialBuilder().build())
                .buildClient();
    }


    @Nullable
    @Bean(name = "sendgridSecret")
    public String sendgridSecret(
            SecretClient secretClient,
            KeyVaultProperties keyVaultProperties){
        return secretClient.getSecret(keyVaultProperties.getSendgrid().getSecret()).getValue();
    }
}
