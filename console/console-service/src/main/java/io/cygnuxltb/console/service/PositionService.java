package io.cygnuxltb.console.service;

import io.cygnuxltb.protocol.http.response.PositionDTO;
import org.springframework.stereotype.Service;

@Service
public final class PositionService {

    public PositionDTO getPosition(int userId) {
        return new PositionDTO();
    }

}
