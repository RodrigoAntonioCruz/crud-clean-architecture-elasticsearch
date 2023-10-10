package br.com.example.entrypoints.mapper;

import br.com.example.core.domain.Product;
import br.com.example.dataproviders.entity.ProductEntity;
import br.com.example.entrypoints.dto.request.ProductRequest;
import br.com.example.entrypoints.dto.response.ProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse toResponse(Product product);

    ProductEntity toDomain(Product product);

    Product toDomain(ProductRequest product);

    Product toDomain(ProductEntity productEntity);

    List<Product> toListDomainRequest(List<ProductRequest> productRequests);

    List<Product> toListDomain(List<ProductEntity> productEntity);

    List<Product> toListDomain(Iterable <ProductEntity> iterable);

    List<ProductResponse> toListResponse(List<Product> products);

    List<ProductEntity> toListEntity(List<Product> products);

}

