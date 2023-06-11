package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TbsProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product Repository
 *
 * @author yellow013
 */
@Repository
public interface ProductDao extends JpaRepository<TbsProduct, Long> {

    /**
     * @param productId int
     * @return ProductEntity
     */
    TbsProduct queryByProductId(int productId);

    /**
     * @param productName String
     * @return ProductEntity
     */
    TbsProduct queryByProductName(String productName);

}
