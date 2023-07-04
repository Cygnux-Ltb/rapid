package io.cygnuxltb.console.service.util;

import io.cygnuxltb.console.persistence.entity.TblBar;
import io.cygnuxltb.console.persistence.entity.TblInstrument;
import io.cygnuxltb.console.persistence.entity.TblInstrumentSettlement;
import io.cygnuxltb.console.persistence.entity.TblParam;
import io.cygnuxltb.console.persistence.entity.TblProduct;
import io.cygnuxltb.console.persistence.entity.TblAccount;
import io.cygnuxltb.console.persistence.entity.TblOrder;
import io.cygnuxltb.console.persistence.entity.TblOrderEvent;
import io.cygnuxltb.console.persistence.entity.TblOrderExt;
import io.cygnuxltb.console.persistence.entity.TblPnl;
import io.cygnuxltb.console.persistence.entity.TblPnlSettlement;
import io.cygnuxltb.console.persistence.entity.TblStrategy;
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

public class DtoConverter {

    public static BarDTO toDTO(TblBar entity) {
        BarDTO dto = new BarDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static AccountDTO toDTO(TblAccount entity) {
        AccountDTO dto = new AccountDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static InstrumentDTO toDTO(TblInstrument entity) {
        InstrumentDTO dto = new InstrumentDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static InstrumentSettlementDTO toDTO(TblInstrumentSettlement entity) {
        InstrumentSettlementDTO dto = new InstrumentSettlementDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static StrategyDTO toDTO(TblStrategy entity) {
        StrategyDTO dto = new StrategyDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderDTO toDTO(TblOrder entity) {
        OrderDTO dto = new OrderDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderEventDTO toDTO(TblOrderEvent entity) {
        OrderEventDTO dto = new OrderEventDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderExtDTO toDTO(TblOrderExt entity) {
        OrderExtDTO dto = new OrderExtDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static PnlDTO toDTO(TblPnl entity) {
        PnlDTO dto = new PnlDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static ParamDTO toDTO(TblParam entity) {
        ParamDTO dto = new ParamDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static ProductDTO toDTO(TblProduct entity) {
        ProductDTO dto = new ProductDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static PnlSettlementDTO toDTO(TblPnlSettlement entity) {
        PnlSettlementDTO dto = new PnlSettlementDTO();
        copyProperties(entity, dto);
        return dto;
    }
}
