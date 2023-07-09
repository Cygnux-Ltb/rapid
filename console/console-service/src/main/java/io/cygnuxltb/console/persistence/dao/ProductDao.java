package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblSProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product Repository
 *
 * @author yellow013
 */
@Repository
public interface ProductDao extends JpaRepository<TblSProduct, Long> {

    /**
     * @param productId int
     * @return ProductEntity
     */
    TblSProduct queryByProductId(int productId);

    /**
     * @param productName String
     * @return ProductEntity
     */
    TblSProduct queryByProductName(String productName);

}
