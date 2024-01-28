package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.BarDao;
import io.cygnuxltb.console.persistence.entity.MkdBarEntity;
import io.cygnuxltb.console.service.util.DtoUtil;
import io.cygnuxltb.protocol.http.response.dto.BarDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

import static io.cygnuxltb.console.persistence.JpaExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.JpaExecutor.select;
import static java.util.stream.Collectors.toList;

@Service
public final class BarService {

    @Resource
    private BarDao dao;

    /**
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<BarDTO>
     */
    public List<BarDTO> getBars(@Nonnull String instrumentCode, int tradingDay) {
        return getBars(instrumentCode, tradingDay, tradingDay);
    }

    /**
     * @param instrumentCode  String
     * @param startTradingDay int
     * @param endTradingDay   int
     * @return List<BarDTO>
     */
    public List<BarDTO> getBars(@Nonnull String instrumentCode, int startTradingDay, int endTradingDay) {
        return select(MkdBarEntity.class,
                () -> dao.queryBy(instrumentCode, startTradingDay, endTradingDay))
                .stream()
                .map(DtoUtil::toDto)
                .collect(toList());
    }

    /**
     * @param entity MkdBarEntity
     * @return boolean
     */
    public boolean putBar(@Nonnull MkdBarEntity entity) {
        return insertOrUpdate(dao, entity);
    }

}
