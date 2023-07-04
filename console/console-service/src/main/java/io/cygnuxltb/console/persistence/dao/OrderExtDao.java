package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblOrderExt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderExt Repository
 *
 * @author yellow013
 */
public interface OrderExtDao extends JpaRepository<TblOrderExt, Long> {

}
