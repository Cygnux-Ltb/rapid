package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.OrderExtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderExt Repository
 *
 * @author yellow013
 */
public interface OrderExtDao extends JpaRepository<OrderExtEntity, Long> {

}
