package br.com.example.entrypoints.dto.request;

import br.com.example.entrypoints.dto.ProductBase;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;



@JsonIgnoreProperties("id")
@EqualsAndHashCode(callSuper = true)
public class ProductRequest extends ProductBase  implements Serializable {

    @Serial
    private static final long serialVersionUID = 5116966424547541322L;

}
