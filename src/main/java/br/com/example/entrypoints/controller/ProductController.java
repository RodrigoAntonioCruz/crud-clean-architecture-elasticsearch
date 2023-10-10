package br.com.example.entrypoints.controller;

import br.com.example.core.usecase.ProductUseCase;
import br.com.example.entrypoints.dto.request.ProductRequest;
import br.com.example.entrypoints.dto.response.ProductResponse;
import br.com.example.entrypoints.mapper.ProductMapper;
import br.com.example.utils.PageUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "Produtos")
@RequestMapping("/products")
public class ProductController {
    private final ProductUseCase productUseCase;

    @PostMapping
    @Operation(description = "Cria um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "409", description = "Duplicidade nos dados informados"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest productRequest) {
        var product = ProductMapper.INSTANCE.toDomain(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.INSTANCE.toResponse(productUseCase.create(product)));
    }

    @PostMapping("/add")
    @Operation(description = "Adiciona uma lista de novos produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Registro criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "409", description = "Duplicidade nos dados informados"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<List<ProductResponse>> add(@Valid @RequestBody List<ProductRequest> productRequest) {
        var product = ProductMapper.INSTANCE.toListDomainRequest(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ProductMapper.INSTANCE.toListResponse(productUseCase.add(product)));
    }

    @PutMapping("/{id}")
    @Operation(description = "Atualiza um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Registro atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado"),
            @ApiResponse(responseCode = "409", description = "Duplicidade nos dados informados"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<ProductResponse> update(@PathVariable String id, @Valid @RequestBody ProductRequest productRequest) {
        var product = ProductMapper.INSTANCE.toDomain(productRequest);
        return ResponseEntity.ok(ProductMapper.INSTANCE.toResponse(productUseCase.update(id, product)));
    }

    @GetMapping("/{id}")
    @Operation(description = "Busca um produto por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados"),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "404", description = "Registro não encontrado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<ProductResponse> findById(@Valid @PathVariable String id) {
        return ResponseEntity.ok(ProductMapper.INSTANCE.toResponse(productUseCase.findById(id)));
    }

    @GetMapping
    @Operation(description = "Busca paginada de produtos por filtros")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitação realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados."),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<Page<ProductResponse>> search(@Valid
                                                        @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                                        @RequestParam(value = "page", defaultValue = "0") Integer page,
                                                        @RequestParam(value = "linesPerPage", defaultValue = "100") Integer linesPerPage,
                                                        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
                                                        @RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
        var list = productUseCase.search(keyword);
        Pageable pageable = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Page<ProductResponse> pages = PageUtils.toPage(list, pageable, list.size(), ProductMapper.INSTANCE::toResponse, orderBy, ProductResponse.class);
        return ResponseEntity.ok().body(pages);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "Remove um produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Solicitação sem conteúdo realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados."),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<Void> deleteById(@Valid @PathVariable String id) {
        productUseCase.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping
    @Operation(description = "Remove uma lista produtos existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Solicitação sem conteúdo realizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Inconsistência nos dados informados."),
            @ApiResponse(responseCode = "401", description = "Acesso não autorizado"),
            @ApiResponse(responseCode = "500", description = "Sistema indisponível no momento")})
    public ResponseEntity<Void> deleteAllById(@Valid @RequestBody Set<String> ids) {
        productUseCase.deleteAllById(ids);
        return ResponseEntity.noContent().build();
    }
}
