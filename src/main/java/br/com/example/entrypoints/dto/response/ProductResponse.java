package br.com.example.entrypoints.dto.response;

import br.com.example.entrypoints.dto.ProductBase;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
public class ProductResponse extends ProductBase implements Serializable {

    @Serial
    private static final long serialVersionUID = -5237884468516326061L;

    public ProductResponse(String id, String name, String description, Double price) {
        super(id, name, description, price);
    }

}