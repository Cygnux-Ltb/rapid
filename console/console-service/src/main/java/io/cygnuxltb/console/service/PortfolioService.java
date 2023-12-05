package io.cygnuxltb.console.service;

import com.github.jsonzou.jmockdata.JMockData;
import io.cygnuxltb.console.persistence.dao.PortfolioDao;
import io.cygnuxltb.console.persistence.entity.TblTrdPortfolio;
import io.cygnuxltb.protocol.http.response.PortfolioDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public final class PortfolioService {

    @Resource
    private PortfolioDao dao;

    private final boolean isMock = true;

    public PortfolioDTO getPortfolio(int userId, String portfolioName) {
        List<String> instrumentCodes = new ArrayList<>();
        if (isMock) {
            instrumentCodes.add(JMockData.mock(String.class));
            instrumentCodes.add(JMockData.mock(String.class));
            instrumentCodes.add(JMockData.mock(String.class));
            instrumentCodes.add(JMockData.mock(String.class));
            instrumentCodes.add(JMockData.mock(String.class));
        } else {
            instrumentCodes = dao.queryBy(userId, portfolioName).stream()
                    .map(TblTrdPortfolio::getInstrumentCode)
                    .collect(Collectors.toList());
        }
        return new PortfolioDTO()
                .setUserId(userId)
                .setPortfolioName(portfolioName)
                .setInstrumentCodes(instrumentCodes
                );
    }

}
