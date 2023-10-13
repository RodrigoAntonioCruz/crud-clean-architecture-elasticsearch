package br.com.example.entrypoints.dto.response;

import br.com.example.entrypoints.dto.ProductBase;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
public class ProductResponse extends ProductBase implements Serializable {

    @Serial
    private static final long serialVersionUID = -5237884468516326061L;

}