package br.com.example.configuration.app;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "spring")
public class AppPropertiesConfiguration {

    private String appVersion;

    private String dataElasticsearchHost;

    private Integer dataElasticsearchPort;
}
