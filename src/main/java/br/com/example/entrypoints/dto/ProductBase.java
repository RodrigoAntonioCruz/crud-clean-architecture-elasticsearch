package br.com.example.entrypoints.dto;

import br.com.example.utils.Constants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class ProductBase implements Serializable {

    @Serial
    private static final long serialVersionUID = 8783509289938906572L;

    @Schema(defaultValue = "Id do produto", example = "64889fb6f3a692337867ea64")
    private String id;

    @NotBlank(message = Constants.NAME_NOT_NULL)
    @Length(min=3, max=120, message = Constants.NAME_MAX_LENGTH)
    @Schema(defaultValue = "Nome do produto", example = "Nívea")
    private String name;

    @NotBlank(message = Constants.DESCRIPTION_NOT_NULL)
    @Length(min=3, max=1000, message = Constants.DESCRIPTION_MAX_LENGTH)
    @Schema(defaultValue = "Descrição de um produto", example = "Hidratante Mustela Stelatopia Pele Ressecada e Atópica 500ml")
    private String description;

    @NotNull(message = Constants.PRICE_NOT_NULL)
    @Schema(defaultValue = "Preço do produto", example = "25.99")
    private double price;
}
