package mx.kabat.bff_product.infrastructure.controller.mapper;

import mx.kabat.bff_product.domain.model.ProductModel;
import mx.kabat.bff_product.domain.model.SimpleProductInfo;
import mx.kabat.bff_product.infrastructure.controller.dto.ProductRequest;
import mx.kabat.bff_product.infrastructure.controller.dto.ProductResponse;
import mx.kabat.bff_product.infrastructure.controller.dto.SimpleProductRequest;

public class ProductMapper {
    public static ProductModel toDomain(ProductRequest entity) {
        return new ProductModel(entity.getIdProduct(),
                entity.getName(),
                entity.getPrice(),
                entity.getStock(),
                entity.getActive());
    }

    public static ProductResponse toResponse(ProductModel model) {
        return new ProductResponse(model.idProduct(),
                model.name(),
                model.price(),
                model.stock(),
                model.active());
    }

    public static SimpleProductInfo toDomain(SimpleProductRequest request) {
        return new SimpleProductInfo(request.getIdProduct(),request.getAmount());
    }



}
