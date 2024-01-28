package io.cygnuxltb.console.service;

import com.github.jsonzou.jmockdata.JMockData;
import io.cygnuxltb.console.SysConfiguration;
import io.cygnuxltb.console.persistence.dao.PnlDao;
import io.cygnuxltb.console.persistence.dao.PnlSettlementDao;
import io.cygnuxltb.console.persistence.entity.TrdPnlEntity;
import io.cygnuxltb.console.persistence.entity.TrdPnlSettlementEntity;
import io.cygnuxltb.console.service.util.DtoUtil;
import io.cygnuxltb.protocol.http.response.dto.PnlDTO;
import io.cygnuxltb.protocol.http.response.dto.PnlSettlementDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static io.cygnuxltb.console.persistence.JpaExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.JpaExecutor.select;

@Service
public final class PnlService {

    @Resource
    private PnlDao dao;

    @Resource
    private PnlSettlementDao settlementDao;

    @Resource
    private SysConfiguration configuration;

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlDTO>
     */
    public List<PnlDTO> getPnl(int strategyId, int tradingDay) {
        if (configuration.isMock()) {
            var mockData = new ArrayList<PnlDTO>();
            mockData.add(JMockData.mock(PnlDTO.class));
            mockData.add(JMockData.mock(PnlDTO.class));
            mockData.add(JMockData.mock(PnlDTO.class));
            return mockData;
        }
        return select(TrdPnlEntity.class,
                () -> dao.queryBy(strategyId, tradingDay))
                .stream()
                .map(DtoUtil::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlSettlementDTO>
     */
    public List<PnlSettlementDTO> getPnlSettlement(int strategyId, int tradingDay) {
        if (configuration.isMock()) {
            var mockData = new ArrayList<PnlSettlementDTO>();
            mockData.add(JMockData.mock(PnlSettlementDTO.class));
            mockData.add(JMockData.mock(PnlSettlementDTO.class));
            mockData.add(JMockData.mock(PnlSettlementDTO.class));
            return mockData;
        }
        return select(TrdPnlSettlementEntity.class,
                () -> settlementDao.queryBy(strategyId, tradingDay))
                .stream()
                .map(DtoUtil::toDto)
                .collect(Collectors.toList())
                ;
    }

    /**
     * @param entity PnlEntity
     * @return boolean
     */
    public boolean putPnl(TrdPnlEntity entity) {
        return insertOrUpdate(dao, entity);
    }

    /**
     * @param entity PnlSettlementEntity
     * @return boolean
     */
    public boolean putPnlSettlement(TrdPnlSettlementEntity entity) {
        return insertOrUpdate(settlementDao, entity);
    }

}
