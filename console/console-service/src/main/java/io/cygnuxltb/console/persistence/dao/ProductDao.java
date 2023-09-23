package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblSysProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product Repository
 *
 * @author yellow013
 */
@Repository
public interface ProductDao extends JpaRepository<TblSysProduct, Long> {

    /**
     * @param productId int
     * @return ProductEntity
     */
    TblSysProduct queryByProductId(int productId);

    /**
     * @param productName String
     * @return ProductEntity
     */
    TblSysProduct queryByProductName(String productName);

}
