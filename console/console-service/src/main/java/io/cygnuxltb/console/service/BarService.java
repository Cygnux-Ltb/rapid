package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.repository.BarRepository;
import io.cygnuxltb.console.persistence.entity.market.BarEntity;
import io.cygnuxltb.console.service.util.DtoUtil;
import io.cygnuxltb.protocol.http.outbound.BarDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

import static io.cygnuxltb.console.persistence.util.DaoExecutor.insertOrUpdate;
import static io.cygnuxltb.console.persistence.util.DaoExecutor.select;

@Service
public final class BarService {

    @Resource
    private BarRepository repo;

    /**
     * @param instrumentCode String
     * @param tradingDay     int
     * @return List<BarEntity>
     */
    public List<BarDTO> getBars(@Nonnull String instrumentCode, int tradingDay) {
        return getBars(instrumentCode, tradingDay, tradingDay);
    }


    /**
     * @param instrumentCode  String
     * @param startTradingDay int
     * @param endTradingDay   int
     * @return List<BarEntity>
     */
    public List<BarDTO> getBars(@Nonnull String instrumentCode, int startTradingDay, int endTradingDay) {
        return select(BarEntity.class,
                () -> repo.queryBy(instrumentCode, startTradingDay, endTradingDay))
                .stream()
                .map(DtoUtil::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * @param entity BarEntity
     * @return boolean
     */
    public boolean putBar(@Nonnull BarEntity entity) {
        return insertOrUpdate(repo, entity);
    }

}
