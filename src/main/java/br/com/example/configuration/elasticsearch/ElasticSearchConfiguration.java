package br.com.example.configuration.elasticsearch;

import br.com.example.configuration.app.AppPropertiesConfiguration;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import lombok.AllArgsConstructor;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class ElasticSearchConfiguration {

    private final AppPropertiesConfiguration configuration;

    @Bean
    public RestClient getRestClient() {
        return RestClient.builder(
                new HttpHost(configuration.getDataElasticsearchHost(),
                             configuration.getDataElasticsearchPort())).build();
    }

    @Bean
    public ElasticsearchTransport getElasticsearchTransport() {
        return new RestClientTransport(
                getRestClient(), new JacksonJsonpMapper());
    }


    @Bean
    public ElasticsearchClient getElasticsearchClient(){
        return new ElasticsearchClient(getElasticsearchTransport());
    }
}