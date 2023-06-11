package io.cygnuxltb.console.persistence.repository;

import io.cygnuxltb.console.persistence.entity.TbsPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Portfolio Repository
 *
 * @author yellow013
 */
@Repository
public interface PortfolioRepository extends JpaRepository<TbsPortfolio, Long> {


    /**
     * @param userId    int
     * @param groupName String
     * @return List<PortfolioEntity>
     */
    List<TbsPortfolio> queryByUserIdAndGroupName(int userId, String groupName);

}
