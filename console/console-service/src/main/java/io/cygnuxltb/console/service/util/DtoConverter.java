package io.cygnuxltb.console.service.util;

import io.cygnuxltb.console.persistence.entity.TbmBar;
import io.cygnuxltb.console.persistence.entity.TbmInstrument;
import io.cygnuxltb.console.persistence.entity.TbmInstrumentSettlement;
import io.cygnuxltb.console.persistence.entity.TbsParam;
import io.cygnuxltb.console.persistence.entity.TbsProduct;
import io.cygnuxltb.console.persistence.entity.TbtAccount;
import io.cygnuxltb.console.persistence.entity.TbtOrder;
import io.cygnuxltb.console.persistence.entity.TbtOrderEvent;
import io.cygnuxltb.console.persistence.entity.TbtOrderExt;
import io.cygnuxltb.console.persistence.entity.TbtPnl;
import io.cygnuxltb.console.persistence.entity.TbtPnlSettlement;
import io.cygnuxltb.console.persistence.entity.TbtStrategy;
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

    public static BarDTO convertToDTO(TbmBar entity) {
        BarDTO dto = new BarDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static AccountDTO convertToDTO(TbtAccount entity) {
        AccountDTO dto = new AccountDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static InstrumentDTO convertToDTO(TbmInstrument entity) {
        InstrumentDTO dto = new InstrumentDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static InstrumentSettlementDTO convertToDTO(TbmInstrumentSettlement entity) {
        InstrumentSettlementDTO dto = new InstrumentSettlementDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static StrategyDTO convertToDTO(TbtStrategy entity) {
        StrategyDTO dto = new StrategyDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderDTO convertToDTO(TbtOrder entity) {
        OrderDTO dto = new OrderDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderEventDTO convertToDTO(TbtOrderEvent entity) {
        OrderEventDTO dto = new OrderEventDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderExtDTO convertToDTO(TbtOrderExt entity) {
        OrderExtDTO dto = new OrderExtDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static PnlDTO convertToDTO(TbtPnl entity) {
        PnlDTO dto = new PnlDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static ParamDTO convertToDTO(TbsParam entity) {
        ParamDTO dto = new ParamDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static ProductDTO convertToDTO(TbsProduct entity) {
        ProductDTO dto = new ProductDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static PnlSettlementDTO convertToDTO(TbtPnlSettlement entity) {
        PnlSettlementDTO dto = new PnlSettlementDTO();
        copyProperties(entity, dto);
        return dto;
    }
}
