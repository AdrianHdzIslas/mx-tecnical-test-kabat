package mx.kabat.bff_product.infrastructure.controller;

import lombok.RequiredArgsConstructor;
import mx.kabat.bff_product.application.port.in.*;
import mx.kabat.bff_product.domain.model.ProductModel;
import mx.kabat.bff_product.domain.model.SimpleProductInfo;
import mx.kabat.bff_product.infrastructure.controller.dto.*;
import mx.kabat.bff_product.infrastructure.controller.mapper.ProductMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final CreateProductPort createProductPort;
    private final GetAllProductPort getAllProductPort;
    private final FindProductPort findProductPort;
    private final CheckAvailabilityPort checkAvailabilityPort;
    private final UpdateStockProductPort updateStockProductPort;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) {
        ProductModel productModel = ProductMapper.toDomain(productRequest);
        ProductModel productModelCreated = createProductPort.createProduct(productModel);
        return ResponseEntity.ok(ProductMapper.toResponse(productModelCreated));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        return ResponseEntity.ok(getAllProductPort
                .findAllProducts()
                .stream()
                .map(ProductMapper::toResponse)
                .toList());
    }

    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Long idProduct) {
        ProductModel productModel = findProductPort.findProduct(idProduct);
        return ResponseEntity.ok(ProductMapper.toResponse(productModel));
    }

    @PostMapping("/check-availability")
    public ResponseEntity<Map> checkAvailability(@RequestBody CheckProductRequest checkProductRequest) {
        return ResponseEntity.ok(checkAvailabilityPort.checkAvailability(checkProductRequest.getProducts()));
    }

    @PatchMapping("/stock")
    public ResponseEntity<Void> updateProduct(@RequestBody UpdateStockRequest updateStockRequest) {
        List<SimpleProductInfo> data = updateStockRequest.getProducts().stream().map(ProductMapper::toDomain).toList();
        updateStockProductPort.updateStockProduct(data);
        return ResponseEntity.noContent().build();
    }

}
