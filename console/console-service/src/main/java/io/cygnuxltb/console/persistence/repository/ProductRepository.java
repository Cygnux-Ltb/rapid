package io.cygnuxltb.console.persistence.repository;

import io.cygnuxltb.console.persistence.entity.sys.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product Repository
 *
 * @author yellow013
 */
@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    /**
     * @param productId int
     * @return ProductEntity
     */
    ProductEntity queryByProductId(int productId);

    /**
     * @param productName String
     * @return ProductEntity
     */
    ProductEntity queryByProductName(String productName);

}
