package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.SysProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Product Repository
 *
 * @author yellow013
 */
@Repository
public interface ProductDao extends JpaRepository<SysProductEntity, Long> {

    /**
     * @param productId int
     * @return ProductEntity
     */
    SysProductEntity queryByProductId(int productId);

    /**
     * @param productName String
     * @return ProductEntity
     */
    SysProductEntity queryByProductName(String productName);

}
