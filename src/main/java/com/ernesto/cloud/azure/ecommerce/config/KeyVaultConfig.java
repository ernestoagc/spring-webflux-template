package com.ernesto.cloud.azure.ecommerce.config;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
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
    @ConfigurationProperties(prefix = "application.ernestotech.azure.key-vault")
    public KeyVaultProperties keyVaultProperties(){
        return new KeyVaultProperties();
    }


    @Bean(name = "secretClient")
    public SecretClient getSecretClient(KeyVaultProperties keyVaultProperties){

        ClientSecretCredential clientSecretCredential = new ClientSecretCredentialBuilder()
                .clientId(keyVaultProperties.getClientId())
                .clientSecret(keyVaultProperties.getClientSecret())
                .tenantId(keyVaultProperties.getTenantId())
                .build();

        return new SecretClientBuilder()
                .vaultUrl(keyVaultProperties.getEndpoint())
                .credential(clientSecretCredential)
                .buildClient();
    }


    @Nullable
    @Bean(name = "serviceBusSecret")
    public String serviceBusSecret(
            SecretClient secretClient,
            KeyVaultProperties keyVaultProperties){
         return secretClient.getSecret(keyVaultProperties.getSecrets().get("serviceBusSecret")).getValue();
    }

    @Nullable
    @Bean(name = "testSecret")
    public String testSecret(
            SecretClient secretClient,
            KeyVaultProperties keyVaultProperties){
        return secretClient.getSecret(keyVaultProperties.getSecrets().get("testSecret")).getValue();
    }

    @Nullable
    @Bean(name = "blobStorageSecret")
    public String blobStorageSecret(
            SecretClient secretClient,
            KeyVaultProperties keyVaultProperties){
        return secretClient.getSecret(keyVaultProperties.getSecrets().get("blobStorageSecret")).getValue();
    }

    @Nullable
    @Bean(name = "appConfigurationSecret")
    public String appConfigurationSecret(
            SecretClient secretClient,
            KeyVaultProperties keyVaultProperties){
        return secretClient.getSecret(keyVaultProperties.getSecrets().get("appConfigurationSecret")).getValue();
    }

}
