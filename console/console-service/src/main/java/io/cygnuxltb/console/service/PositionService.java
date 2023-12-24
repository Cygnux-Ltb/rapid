package io.cygnuxltb.console.service;

import com.github.jsonzou.jmockdata.JMockData;
import io.cygnuxltb.console.SysConfiguration;
import io.cygnuxltb.protocol.http.response.PositionsDTO;
import io.cygnuxltb.protocol.http.response.PositionsDTO.Position;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public final class PositionService {

    @Resource
    private SysConfiguration configuration;

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
