package io.cygnuxltb.console.persistence.repository;

import io.cygnuxltb.console.persistence.entity.sys.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Portfolio Repository
 *
 * @author yellow013
 */
@Repository
public interface PortfolioRepository extends JpaRepository<PortfolioEntity, Long> {


    List<PortfolioEntity> queryByUserIdAndGroupName(int userId, String groupName);

}
