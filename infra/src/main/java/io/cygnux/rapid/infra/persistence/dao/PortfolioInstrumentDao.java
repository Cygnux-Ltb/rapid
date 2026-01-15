package io.cygnux.rapid.infra.persistence.dao;

import io.cygnux.rapid.infra.persistence.entity.PortfolioInstrumentEntity;
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
public interface PortfolioInstrumentDao extends JpaRepository<PortfolioInstrumentEntity, Long> {

    /**
     * @param userid        int
     * @param portfolioCode String
     * @return List<TPortfolioInstrumentEntity>
     */
    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND e.userid = :userid
                AND e.portfolioCode = :portfolioCode
            """)
    List<PortfolioInstrumentEntity> queryInstrumentList(@Param("userid") int userid,
                                                        @Param("portfolioCode") String portfolioCode);

    @Query("""
            SELECT e FROM #{#entityName} e WHERE 1 = 1
                AND e.userid = :userid
                AND e.portfolioCode = :portfolioCode
                AND e.instrumentCode = :instrumentCode
            """)
    PortfolioInstrumentEntity queryInstrument(@Param("userid") int userid,
                                              @Param("portfolioCode") String portfolioCode,
                                              @Param("instrumentCode") String instrumentCode);

    /**
     * @param userid        int
     * @param portfolioCode String
     * @return int
     */
    int countByUseridAndPortfolioCode(int userid, String portfolioCode);


    @Modifying
    @Query("""
            DELETE FROM #{#entityName} e WHERE 1 = 1
                AND e.userid = :userid
                AND e.portfolioCode = :portfolioCode
            """)
    int deleteAllInstruments(int userid, String portfolioCode);


    @Modifying
    @Query("""
            DELETE FROM #{#entityName} e WHERE 1 = 1
                AND e.userid = :userid
                AND e.portfolioCode = :portfolioCode
                AND e.instrumentCode IN (:instrumentCodes)
            """)
    int deleteInstruments(int userid, String portfolioCode, List<String> instrumentCodes);

}
