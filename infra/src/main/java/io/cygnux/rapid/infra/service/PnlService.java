package io.cygnux.rapid.infra.service;

import io.cygnux.rapid.infra.component.ApplicationConfiguration;
import io.cygnux.rapid.infra.dto.resp.PnlRsp;
import io.cygnux.rapid.infra.dto.resp.PnlSettlementRsp;
import io.cygnux.rapid.infra.persistence.dao.PnlDao;
import io.cygnux.rapid.infra.persistence.dao.PnlSettlementDao;
import io.cygnux.rapid.infra.persistence.entity.PnlEntity;
import io.cygnux.rapid.infra.persistence.entity.PnlSettlementEntity;
import io.cygnux.rapid.infra.service.util.RespConverter;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

import static io.cygnux.rapid.infra.persistence.JpaExecutor.insert;
import static io.cygnux.rapid.infra.persistence.JpaExecutor.select;

@Service
public class PnlService {

    @Resource
    private PnlDao pnlDao;

    @Resource
    private PnlSettlementDao pnlSettlementDao;

    @Resource
    private ApplicationConfiguration configuration;

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlDTO>
     */
    public List<PnlRsp> getPnl(int strategyId, int tradingDay) {
        return select(PnlEntity.class,
                () -> pnlDao.queryBy(strategyId, tradingDay))
                .stream()
                .map(RespConverter::fromEntity)
                .toList();
    }

    /**
     * @param strategyId int
     * @param tradingDay int
     * @return List<PnlSettlementDTO>
     */
    public List<PnlSettlementRsp> getPnlSettlement(int strategyId, int tradingDay) {
        return select(PnlSettlementEntity.class,
                () -> pnlSettlementDao.queryBy(strategyId, tradingDay))
                .stream()
                .map(RespConverter::fromEntity)
                .toList();
    }

    /**
     * @param entity PnlEntity
     * @return boolean
     */
    public boolean putPnl(PnlEntity entity) {
        return insert(pnlDao, entity);
    }

    /**
     * @param entity PnlEntity
     * @return boolean
     */
    public boolean putPnl(PnlRsp entity) {
        return false;
    }

    /**
     * @param entity PnlSettlementEntity
     * @return boolean
     */
    public boolean putPnlSettlement(PnlSettlementEntity entity) {
        return insert(pnlSettlementDao, entity);
    }

}
