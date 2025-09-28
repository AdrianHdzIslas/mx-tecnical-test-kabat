package mx.kabat.bff_product.infrastructure.persistence.mapper;

import mx.kabat.bff_product.domain.model.ProductInfoModel;
import mx.kabat.bff_product.domain.model.ProductModel;
import mx.kabat.bff_product.infrastructure.persistence.entity.ProductEntity;

public class ProductMapper {
    public static ProductModel toDomain(ProductEntity entity) {
        return new ProductModel(entity.getIdProduct(),
                entity.getName(),
                entity.getPrice(),
                entity.getStock(),
                entity.getActive());
    }

    public static ProductEntity toEntity(ProductModel product) {
        ProductEntity entity = new ProductEntity();
        entity.setIdProduct(product.idProduct());
        entity.setName(product.name());
        entity.setPrice(product.price());
        entity.setStock(product.stock());
        entity.setActive(product.active());
        return entity;
    }

    public static ProductInfoModel toDomainInfo(ProductEntity productEntity) {
       return new ProductInfoModel(
               productEntity.getIdProduct()
               , productEntity.getPrice()
                , productEntity.getStock()
                , productEntity.getActive());
    }
}
