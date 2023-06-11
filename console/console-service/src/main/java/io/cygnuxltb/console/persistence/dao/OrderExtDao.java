package io.cygnuxltb.console.persistence.repository;

import io.cygnuxltb.console.persistence.entity.TbtOrderExt;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderExt Repository
 *
 * @author yellow013
 */
public interface OrderExtRepository extends JpaRepository<TbtOrderExt, Long> {

}
