package io.cygnuxltb.console.service;

import com.github.jsonzou.jmockdata.JMockData;
import io.cygnuxltb.console.SysConfiguration;
import io.cygnuxltb.console.persistence.dao.PortfolioDao;
import io.cygnuxltb.console.persistence.entity.TrdPortfolioEntity;
import io.cygnuxltb.protocol.http.response.dto.PortfolioDTO;
import io.cygnuxltb.protocol.http.response.dto.TargetPoolDTO;
import io.cygnuxltb.protocol.http.response.dto.TargetPoolDTO.TargetObj;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public final class PortfolioService {

    @Resource
    private PortfolioDao dao;

    @Resource
    private SysConfiguration configuration;

    /**
     * @param userId        int
     * @param portfolioName String
     * @return PortfolioDTO
     */
    public PortfolioDTO getPortfolio(int userId, String portfolioName) {
        List<String> instrumentCodes = new ArrayList<>();
        if (configuration.isMock()) {
            instrumentCodes.add(JMockData.mock(String.class));
            instrumentCodes.add(JMockData.mock(String.class));
            instrumentCodes.add(JMockData.mock(String.class));
            instrumentCodes.add(JMockData.mock(String.class));
            instrumentCodes.add(JMockData.mock(String.class));
        } else {
            instrumentCodes = dao.queryBy(userId, portfolioName).stream()
                    .map(TrdPortfolioEntity::getInstrumentCode)
                    .collect(Collectors.toList());
        }
        return new PortfolioDTO()
                .setUserId(userId)
                .setPortfolioName(portfolioName)
                .setInstrumentCodes(instrumentCodes
                );
    }

    /**
     * @param userId        int
     * @param portfolioName String
     * @return TargetPoolDTO
     */
    public TargetPoolDTO getTargetPool(int userId, String portfolioName) {
        List<TargetObj> targetList = new ArrayList<>();
        if (configuration.isMock()) {
            targetList.add(JMockData.mock(TargetObj.class));
            targetList.add(JMockData.mock(TargetObj.class));
            targetList.add(JMockData.mock(TargetObj.class));
            targetList.add(JMockData.mock(TargetObj.class));
            targetList.add(JMockData.mock(TargetObj.class));
        } else {
            List<String> instrumentCodes = dao.queryBy(userId, portfolioName).stream()
                    .map(TrdPortfolioEntity::getInstrumentCode)
                    .toList();
        }
        return new TargetPoolDTO()
                .setUserId(userId)
                .setPortfolioName(portfolioName)
                .setTargetList(targetList);
    }

}
