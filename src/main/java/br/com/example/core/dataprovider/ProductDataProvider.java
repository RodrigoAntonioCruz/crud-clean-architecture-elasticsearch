package br.com.example.core.dataprovider;


import br.com.example.core.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface ProductDataProvider {
    Product save(Product product);
    Optional<Product> findById(String id);
    List<Product> search(String keyword);
    void deleteById(String id);
    void deleteAllById(Set<String> ids);
    List<Product> saveAll(List<Product> product);
}
