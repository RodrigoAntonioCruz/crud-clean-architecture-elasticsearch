package br.com.example.dataproviders.repository;

import br.com.example.dataproviders.entity.ProductEntity;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ProductEntityRepository extends ElasticsearchRepository<ProductEntity, String> {

    @Query("{\"multi_match\": {\"query\": \"?0\", \"fields\": [\"*\"], \"fuzziness\": \"AUTO\"}}")
    List<ProductEntity> findByProductKeyword(String keyword);

}
