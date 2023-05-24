package io.cygnuxltb.console.service.util;

import io.cygnuxltb.console.persistence.entity.AccountEntity;
import io.cygnuxltb.console.persistence.entity.BarEntity;
import io.cygnuxltb.console.persistence.entity.InstrumentEntity;
import io.cygnuxltb.console.persistence.entity.InstrumentSettlementEntity;
import io.cygnuxltb.console.persistence.entity.OrderEntity;
import io.cygnuxltb.console.persistence.entity.OrderEventEntity;
import io.cygnuxltb.console.persistence.entity.OrderExtEntity;
import io.cygnuxltb.console.persistence.entity.ParamEntity;
import io.cygnuxltb.console.persistence.entity.PnlEntity;
import io.cygnuxltb.console.persistence.entity.PnlSettlementEntity;
import io.cygnuxltb.console.persistence.entity.ProductEntity;
import io.cygnuxltb.console.persistence.entity.StrategyEntity;
import io.cygnuxltb.protocol.http.outbound.AccountDTO;
import io.cygnuxltb.protocol.http.outbound.BarDTO;
import io.cygnuxltb.protocol.http.outbound.InstrumentDTO;
import io.cygnuxltb.protocol.http.outbound.InstrumentSettlementDTO;
import io.cygnuxltb.protocol.http.outbound.OrderDTO;
import io.cygnuxltb.protocol.http.outbound.OrderEventDTO;
import io.cygnuxltb.protocol.http.outbound.OrderExtDTO;
import io.cygnuxltb.protocol.http.outbound.ParamDTO;
import io.cygnuxltb.protocol.http.outbound.PnlDTO;
import io.cygnuxltb.protocol.http.outbound.PnlSettlementDTO;
import io.cygnuxltb.protocol.http.outbound.ProductDTO;
import io.cygnuxltb.protocol.http.outbound.StrategyDTO;

import static org.springframework.beans.BeanUtils.copyProperties;

public class DtoUtil {

    public static BarDTO convertToDTO(BarEntity entity) {
        BarDTO dto = new BarDTO();
        copyProperties(entity, dto);
        return dto;
//        return new BarDTO().setInstrumentCode(entity.getInstrumentCode())
//                .setTradingDay(entity.getTradingDay())
//                .setActualDate(entity.getActualDate())
//                .setTimePoint(entity.getTimePoint())
//                .setOpen(entity.getOpen())
//                .setHigh(entity.getHigh())
//                .setLow(entity.getLow())
//                .setClose(entity.getClose())
//                .setVolume(entity.getVolume())
//                .setTurnover(entity.getTurnover());
    }

    public static AccountDTO convertToDTO(AccountEntity entity) {
        AccountDTO dto = new AccountDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static InstrumentDTO convertToDTO(InstrumentEntity entity) {
        InstrumentDTO dto = new InstrumentDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static InstrumentSettlementDTO convertToDTO(InstrumentSettlementEntity entity) {
        InstrumentSettlementDTO dto = new InstrumentSettlementDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static StrategyDTO convertToDTO(StrategyEntity entity) {
        StrategyDTO dto = new StrategyDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderDTO convertToDTO(OrderEntity entity) {
        OrderDTO dto = new OrderDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderEventDTO convertToDTO(OrderEventEntity entity) {
        OrderEventDTO dto = new OrderEventDTO();
        copyProperties(entity, dto);
        return dto;
    }


    public static OrderExtDTO convertToDTO(OrderExtEntity entity) {
        OrderExtDTO dto = new OrderExtDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static PnlDTO convertToDTO(PnlEntity entity) {
        PnlDTO dto = new PnlDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static ParamDTO convertToDTO(ParamEntity entity) {
        ParamDTO dto = new ParamDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static ProductDTO convertToDTO(ProductEntity entity) {
        ProductDTO dto = new ProductDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static PnlSettlementDTO convertToDTO(PnlSettlementEntity entity) {
        PnlSettlementDTO dto = new PnlSettlementDTO();
        copyProperties(entity, dto);
        return dto;
    }
}
