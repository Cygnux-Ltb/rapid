package io.cygnuxltb.console.service;

import io.cygnuxltb.protocol.http.response.PositionsDTO;
import org.springframework.stereotype.Service;

@Service
public final class PositionService {

    public PositionsDTO getPosition(int userId) {
        return new PositionsDTO();
    }

}
