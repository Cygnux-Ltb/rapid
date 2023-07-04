package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product Repository
 *
 * @author yellow013
 */
@Repository
public interface ProductDao extends JpaRepository<TblProduct, Long> {

    /**
     * @param productId int
     * @return ProductEntity
     */
    TblProduct queryByProductId(int productId);

    /**
     * @param productName String
     * @return ProductEntity
     */
    TblProduct queryByProductName(String productName);

}
