package br.com.example.core.usecase;

import br.com.example.core.domain.Product;

import java.util.List;
import java.util.Set;

public interface ProductUseCase {

    Product create(Product product);

    Product update(String id, Product product);

    Product findById(String id);

    List<Product> search(String keyword);

    void deleteById(String id);

    void deleteAllById(Set<String> ids);

    List<Product> add(List<Product> product);
}
