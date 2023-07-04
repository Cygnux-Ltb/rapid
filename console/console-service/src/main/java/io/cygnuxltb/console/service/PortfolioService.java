package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.PortfolioDao;
import io.cygnuxltb.console.persistence.entity.TblPortfolio;
import io.cygnuxltb.protocol.http.outbound.PortfolioDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public final class PortfolioService {

    @Resource
    private PortfolioDao dao;

    public PortfolioDTO getPortfolio(int userId, String portfolioName) {
        List<TblPortfolio> entities = dao.queryBy(userId, portfolioName);
        return new PortfolioDTO()
                .setUserId(userId)
                .setPortfolioName(portfolioName)
                .setInstrumentCodes(entities == null
                        ? new ArrayList<>()
                        : entities
                        .stream()
                        .map(TblPortfolio::getInstrumentCode)
                        .collect(Collectors.toList()));
    }

}
