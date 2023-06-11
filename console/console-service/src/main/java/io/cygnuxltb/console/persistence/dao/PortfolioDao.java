package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TbsPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Portfolio Repository
 *
 * @author yellow013
 */
@Repository
public interface PortfolioDao extends JpaRepository<TbsPortfolio, Long> {


    /**
     * @param userId    int
     * @param groupName String
     * @return List<PortfolioEntity>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND e.userId = :userId "
            + " AND e.groupName = :groupName "
    )
    List<TbsPortfolio> queryBy(int userId, String groupName);

}
