package io.cygnux.rapid.infra.service;

import io.cygnux.rapid.infra.component.ApplicationConfiguration;
import io.cygnux.rapid.infra.dto.resp.PositionsRsp;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PositionService {

    @Resource
    private ApplicationConfiguration configuration;

    /**
     * @param userId int
     * @return PositionsDTO
     */
    public PositionsRsp getPosition(int userId) {
        // TODO IS MOCK
        var positions = new ArrayList<PositionsRsp.PositionObj>();
        return new PositionsRsp().setUserid(userId)
                .setPositions(positions);
    }

}
