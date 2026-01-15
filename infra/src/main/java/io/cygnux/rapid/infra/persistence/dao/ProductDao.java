package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product Repository
 *
 * @author yellow013
 */
@Repository
public interface ProductDao extends JpaRepository<ProductEntity, Integer> {

    /**
     * @param productId int
     * @return SProductEntity
     */
    ProductEntity queryByProductId(int productId);

    /**
     * @param productCode String
     * @return SProductEntity
     */
    ProductEntity queryByProductCode(String productCode);

}
