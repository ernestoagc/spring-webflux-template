package com.ernesto.cloud.azure.ecommerce.config;

import com.azure.data.appconfiguration.ConfigurationAsyncClient;
import com.azure.data.appconfiguration.ConfigurationClient;
import com.azure.data.appconfiguration.ConfigurationClientBuilder;
import com.azure.data.appconfiguration.models.ConfigurationSetting;
import com.azure.security.keyvault.secrets.SecretClient;
import com.ernesto.cloud.azure.ecommerce.properties.AppConfigurationProperties;
import com.ernesto.cloud.azure.ecommerce.properties.KeyVaultProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Configuration
public class AppConfigurationConfig {

    private final String appConfigurationSecret;

    @Bean
    @ConfigurationProperties(prefix = "application.ernestotech.azure.app-configuration")
    public AppConfigurationProperties appConfigurationProperties(){
        return new AppConfigurationProperties();
    }

    @Bean
    ConfigurationClient ConfigurationClient(){
        return new ConfigurationClientBuilder()
                .connectionString(appConfigurationSecret)
                .buildClient();
    }

    @Nullable
    @Bean(name = "blobStorageName")
    public String blobStorageNameKey(
            ConfigurationClient configurationClient,
            AppConfigurationProperties appConfigurationProperties){
        return configurationClient.getConfigurationSetting("bsErnestoTechName","local").getValue();
    }

}
