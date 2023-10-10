package br.com.example.entrypoints.dto.request;

import br.com.example.entrypoints.dto.ProductBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;


@JsonIgnoreProperties("id")
@EqualsAndHashCode(callSuper = true)
public class ProductRequest extends ProductBase  implements Serializable {

    @Serial
    private static final long serialVersionUID = 5116966424547541322L;

    public ProductRequest(String id, String name, String description, Double price) {
        super(id, name, description, price);
    }

}
