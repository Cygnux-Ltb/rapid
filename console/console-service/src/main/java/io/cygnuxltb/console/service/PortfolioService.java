package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.dao.PortfolioDao;
import io.cygnuxltb.console.persistence.entity.TbsPortfolio;
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

    public PortfolioDTO getPortfolio(int userId, String groupName) {
        List<TbsPortfolio> entities = dao.queryByUserIdAndGroupName(userId, groupName);
        return new PortfolioDTO()
                .setUserId(userId)
                .setGroupName(groupName)
                .setInstrumentCodes(entities == null
                        ? new ArrayList<>()
                        : entities
                        .stream()
                        .map(TbsPortfolio::getInstrumentCode)
                        .collect(Collectors.toList()));
    }

}
