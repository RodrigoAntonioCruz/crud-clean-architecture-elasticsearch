package br.com.example.dataproviders.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "product-index")
public class ProductEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -4193301264047593807L;

    @Id
    private String id;

    private String name;

    private String description;

    private Double price;

}
