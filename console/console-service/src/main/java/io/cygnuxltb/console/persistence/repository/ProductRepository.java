package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.dao.base.BaseJpaRepository;
import io.cygnuxltb.console.persistence.entity.ProductEntity;
import org.springframework.stereotype.Repository;

/**
 * Product DAO
 *
 * @author yellow013
 */
@Repository
public interface ProductRepository extends BaseJpaRepository<ProductEntity> {

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
