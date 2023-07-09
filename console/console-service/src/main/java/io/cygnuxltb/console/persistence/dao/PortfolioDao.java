package io.cygnuxltb.console.persistence.dao;

import io.cygnuxltb.console.persistence.entity.TblTPortfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Portfolio Repository
 *
 * @author yellow013
 */
@Repository
public interface PortfolioDao extends JpaRepository<TblTPortfolio, Long> {


    /**
     * @param userId        int
     * @param portfolioName String
     * @return List<PortfolioEntity>
     */
    @Query("SELECT e FROM #{#entityName} e WHERE 1 = 1"
            + " AND e.userId = :userId "
            + " AND e.portfolioName = :portfolioName "
    )
    List<TblTPortfolio> queryBy(@Param("userId") int userId,
                                @Param("portfolioName") String portfolioName);

}
