package io.cygnuxltb.console.service;

import io.cygnuxltb.console.persistence.entity.sys.PortfolioEntity;
import io.cygnuxltb.console.persistence.repository.PortfolioRepository;
import io.cygnuxltb.protocol.http.outbound.PortfolioDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public final class PortfolioService {

    @Resource
    private PortfolioRepository repo;

    public PortfolioDTO getPortfolio(int userId, String groupName) {
        List<PortfolioEntity> entities = repo.queryByUserIdAndGroupName(userId, groupName);
        PortfolioDTO dto = new PortfolioDTO().setUserId(userId).setGroupName(groupName);
        Optional.ofNullable(entities)
                .ifPresent(list -> dto
                        .setInstrumentCodes(list
                                .stream()
                                .map(PortfolioEntity::getInstrumentCode)
                                .collect(Collectors.toList())));
        return dto;
    }

}
