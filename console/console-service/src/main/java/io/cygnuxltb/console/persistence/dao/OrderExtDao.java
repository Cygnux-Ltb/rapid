package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TrdOrderExtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderExt Repository
 *
 * @author yellow013
 */
public interface OrderExtDao extends JpaRepository<TrdOrderExtEntity, Long> {

}
