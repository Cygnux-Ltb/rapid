package io.cygnuxltb.console.service;

import com.github.jsonzou.jmockdata.JMockData;
import io.cygnuxltb.protocol.http.response.PositionsDTO;
import io.cygnuxltb.protocol.http.response.PositionsDTO.Position;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public final class PositionService {

    private final boolean isMock = true;

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
