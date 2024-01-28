package io.cygnuxltb.console.service;

import com.github.jsonzou.jmockdata.JMockData;
import io.cygnuxltb.console.SysConfiguration;
import io.cygnuxltb.protocol.http.response.dto.PositionsDTO;
import io.cygnuxltb.protocol.http.response.dto.PositionsDTO.Position;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public final class PositionService {

    @Resource
    private SysConfiguration configuration;

    /**
     * @param userId int
     * @return PositionsDTO
     */
    public PositionsDTO getPosition(int userId) {
        // TODO IS MOCK
        var positions = new ArrayList<Position>();
        positions.add(JMockData.mock(Position.class));
        positions.add(JMockData.mock(Position.class));
        positions.add(JMockData.mock(Position.class));
        positions.add(JMockData.mock(Position.class));
        positions.add(JMockData.mock(Position.class));
        return new PositionsDTO().setUserId(userId)
                .setPositions(positions);
    }

}
