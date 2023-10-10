package br.com.example.dataproviders.repository;


import br.com.example.core.dataprovider.ProductDataProvider;
import br.com.example.core.domain.Product;
import br.com.example.entrypoints.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
@AllArgsConstructor
public class ProductImplRepository implements ProductDataProvider {

    private final ProductEntityRepository productRepository;

    @Override
    public Product save(Product product) {
        var entity = ProductMapper.INSTANCE.toDomain(product);
        return ProductMapper.INSTANCE.toDomain(productRepository.save(entity));
    }

    @Override
    public Optional<Product> findById(String id) {
        return productRepository.findById(id).map(ProductMapper.INSTANCE::toDomain);
    }

    @Override
    public List<Product> search(String keyword) {
        if (keyword.isEmpty()) {
            return ProductMapper.INSTANCE.toListDomain(productRepository.findAll());
        } else {
            return ProductMapper.INSTANCE.toListDomain(productRepository.findByProductKeyword(keyword));
        }
    }

    @Override
    public void deleteById(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAllById(Set<String> ids) {
        productRepository.deleteAllById(ids);
    }

    @Override
    public List<Product> saveAll(List<Product> products) {
        var entities = ProductMapper.INSTANCE.toListEntity(products);
        return ProductMapper.INSTANCE.toListDomain(productRepository.saveAll(entities));
    }
}
