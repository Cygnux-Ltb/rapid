package io.cygnux.rapid.infra.service;

import io.cygnux.rapid.infra.dto.resp.BarRsp;
import io.cygnux.rapid.infra.persistence.dao.BarDao;
import io.cygnux.rapid.infra.persistence.entity.BarEntity;
import io.cygnux.rapid.infra.service.util.RespConverter;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

import static io.cygnux.rapid.infra.persistence.JpaExecutor.insert;
import static io.cygnux.rapid.infra.persistence.JpaExecutor.select;


@Service
public class BarService {

    @Resource
    private BarDao barDao;

    /**
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<BarRsp>
     */
    public List<BarRsp> getBars(@Nonnull String instrumentCode, int tradingDay) {
        return getBars(instrumentCode, tradingDay, tradingDay);
    }

    /**
     * @param instrumentCode  String
     * @param startTradingDay int
     * @param endTradingDay   int
     * @return List<BarRsp>
     */
    public List<BarRsp> getBars(@Nonnull String instrumentCode, int startTradingDay, int endTradingDay) {
        return select(BarEntity.class,
                () -> barDao.queryBy(instrumentCode, startTradingDay, endTradingDay))
                .stream()
                .map(RespConverter::fromEntity)
                .toList();
    }

    /**
     * @param entity BarEntity
     * @return boolean
     */
    public boolean putBar(@Nonnull BarEntity entity) {
        return insert(barDao, entity);
    }

    /**
     * @param rsp BarRsp
     * @return boolean
     */
    public boolean putBar(@Nonnull BarRsp rsp) {
        return false;
    }

}
