package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.PortfolioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
public interface PortfolioDao extends JpaRepository<PortfolioEntity, Long> {

    /**
     * @param userid int
     * @return List<PortfolioVo>
     */
//    @Query("""
//            SELECT DISTINCT
//                new io.cygnuxltb.console.persistence.vo.PortfolioVo(e.portfolioCode, e.portfolioName)
//                FROM #{#entityName} e WHERE 1 = 1
//                AND (e.userid = :userid)
//            """)
//    List<PortfolioVo> queryPortfolios(@Param("userid") int userid);
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND (e.userid = :userid)
            """)
    List<PortfolioEntity> queryPortfolioList(@Param("userid") int userid);

    /**
     * @param userid        int
     * @param portfolioCode String
     * @return List<TPortfolioEntity>
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND e.userid = :userid
                AND e.portfolioCode = :portfolioCode
            """)
    PortfolioEntity queryPortfolio(@Param("userid") int userid,
                                   @Param("portfolioCode") String portfolioCode);

    /**
     * @param userid        int
     * @param portfolioCode String
     * @return int
     */
    int countByUseridAndPortfolioCode(int userid, String portfolioCode);

    /**
     * @param userid        int
     * @param portfolioCode String
     * @return int
     */
    @Modifying
    @Query("""
            DELETE FROM #{#entityName} e WHERE 1 = 1
                AND e.userid = :userid
                AND e.portfolioCode = :portfolioCode
            """)
    int deletePortfolio(int userid, String portfolioCode);

}
