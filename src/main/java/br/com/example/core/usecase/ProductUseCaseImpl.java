package br.com.example.core.usecase;

import br.com.example.core.dataprovider.ProductDataProvider;
import br.com.example.core.domain.Product;

import java.util.List;
import java.util.Set;

public class ProductUseCaseImpl implements ProductUseCase {

    private final ProductDataProvider productDataProvider;

    public ProductUseCaseImpl(ProductDataProvider productDataProvider) {
        this.productDataProvider = productDataProvider;
    }

    @Override
    public Product create(Product product) {
        return productDataProvider.save(product);
    }

    @Override
    public Product update(String id, Product product) {
        var existingProduct = findProductById(id);

        existingProduct.setId(id);
        existingProduct.setName(product.getName());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setPrice(product.getPrice());

        return productDataProvider.save(existingProduct);
    }

    @Override
    public Product findById(String id) {
        return findProductById(id);
    }

    @Override
    public List<Product> search(String keyword) {
        return productDataProvider.search(keyword);
    }

    private Product findProductById(String id) {
        return productDataProvider.findById(id)
                .orElseThrow(() -> new NotFoundException(Constants.NOT_FOUND_PRODUCT));
    }

    @Override
    public void deleteById(String id) {
        var product = findProductById(id);
        this.productDataProvider.deleteById(product.getId());
    }

    @Override
    public void deleteAllById(Set<String> ids) {
        for (String id : ids) {
            if(!findProductById(id).getId().isEmpty()) {
                productDataProvider.deleteById(id);
            }
        }
    }
    @Override
    public List<Product> add(List<Product> product) {
        return productDataProvider.saveAll(product);
    }
}
