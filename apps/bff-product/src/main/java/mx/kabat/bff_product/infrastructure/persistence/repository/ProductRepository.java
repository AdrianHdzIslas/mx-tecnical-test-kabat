package mx.kabat.bff_product.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import mx.kabat.bff_product.application.port.out.ProductRepositoryPort;
import mx.kabat.bff_product.domain.model.ProductInfoModel;
import mx.kabat.bff_product.domain.model.ProductModel;
import mx.kabat.bff_product.domain.model.SimpleProductInfo;
import mx.kabat.bff_product.infrastructure.persistence.entity.ProductEntity;
import mx.kabat.bff_product.infrastructure.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductRepository implements ProductRepositoryPort {

    private final ProductJpaRepository productJpaRepository;

    @Override
    public ProductModel save(ProductModel product) {
        ProductEntity productEntity = ProductMapper.toEntity(product);
        ProductEntity productEntitySaved = productJpaRepository.save(productEntity);
        return ProductMapper.toDomain(productEntitySaved);
    }

    @Override
    public List<ProductModel> findAllProducts() {
        return productJpaRepository
                .findAll()
                .stream()
                .map(ProductMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<ProductModel> findById(Long idProduct) {
        Optional<ProductEntity> productEntitydOptional
                = productJpaRepository.findById(idProduct);
        return productEntitydOptional.map(ProductMapper::toDomain);
    }

    @Override
    public Map<Long, ProductInfoModel> findByIds(List<Long> idProducts) {
        return productJpaRepository
                .findAllById(idProducts)
                .stream()
                .map(ProductMapper::toDomainInfo)
                .collect(Collectors
                        .toMap(
                                ProductInfoModel::idProduct,
                                productEntity ->
                                      productEntity
                        ));
    }

    @Override
    @Transactional
    public void updateStockProduct(List<SimpleProductInfo> simpleProductInfo) {
        Map<Long, Integer> stockChanges = simpleProductInfo.stream()
                .collect(Collectors.toMap(
                        SimpleProductInfo::idProduct,
                        SimpleProductInfo::amount
                ));

        List<ProductEntity> entities = productJpaRepository.findAllById(stockChanges.keySet());

        List<ProductEntity> updated = entities.stream()
                .map(ProductMapper::toDomain)
                .map(product -> {
                    int change = stockChanges.get(product.idProduct());
                    return product.withUpdatedStock(change);
                })
                .map(ProductMapper::toEntity)
                .toList();

        productJpaRepository.saveAll(updated);
    }


}
