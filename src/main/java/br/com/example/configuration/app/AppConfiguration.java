package br.com.example.configuration.app;

import br.com.example.core.dataprovider.ProductDataProvider;
import br.com.example.core.usecase.ProductUseCase;
import br.com.example.core.usecase.ProductUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ProductUseCase productUseCase(ProductDataProvider productDataProvider) {
        return new ProductUseCaseImpl(productDataProvider);
    }
}
