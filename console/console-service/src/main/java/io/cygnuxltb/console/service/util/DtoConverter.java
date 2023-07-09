package io.cygnuxltb.console.service.util;

import io.cygnuxltb.console.persistence.entity.TblMBar;
import io.cygnuxltb.console.persistence.entity.TblMInstrument;
import io.cygnuxltb.console.persistence.entity.TblMInstrumentSettlement;
import io.cygnuxltb.console.persistence.entity.TblSParam;
import io.cygnuxltb.console.persistence.entity.TblSProduct;
import io.cygnuxltb.console.persistence.entity.TblTAccount;
import io.cygnuxltb.console.persistence.entity.TblTOrder;
import io.cygnuxltb.console.persistence.entity.TblTOrderEvent;
import io.cygnuxltb.console.persistence.entity.TblTOrderExt;
import io.cygnuxltb.console.persistence.entity.TblTPnl;
import io.cygnuxltb.console.persistence.entity.TblTPnlSettlement;
import io.cygnuxltb.console.persistence.entity.TblTStrategy;
import io.cygnuxltb.protocol.http.response.AccountDTO;
import io.cygnuxltb.protocol.http.response.BarDTO;
import io.cygnuxltb.protocol.http.response.InstrumentDTO;
import io.cygnuxltb.protocol.http.response.InstrumentSettlementDTO;
import io.cygnuxltb.protocol.http.response.OrderDTO;
import io.cygnuxltb.protocol.http.response.OrderEventDTO;
import io.cygnuxltb.protocol.http.response.OrderExtDTO;
import io.cygnuxltb.protocol.http.response.ParamDTO;
import io.cygnuxltb.protocol.http.response.PnlDTO;
import io.cygnuxltb.protocol.http.response.PnlSettlementDTO;
import io.cygnuxltb.protocol.http.response.ProductDTO;
import io.cygnuxltb.protocol.http.response.StrategyDTO;

import static org.springframework.beans.BeanUtils.copyProperties;

public class DtoConverter {

    public static BarDTO toDTO(TblMBar entity) {
        var dto = new BarDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static AccountDTO toDTO(TblTAccount entity) {
        var dto = new AccountDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static InstrumentDTO toDTO(TblMInstrument entity) {
        var dto = new InstrumentDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static InstrumentSettlementDTO toDTO(TblMInstrumentSettlement entity) {
        var dto = new InstrumentSettlementDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static StrategyDTO toDTO(TblTStrategy entity) {
        var dto = new StrategyDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderDTO toDTO(TblTOrder entity) {
        var dto = new OrderDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderEventDTO toDTO(TblTOrderEvent entity) {
        var dto = new OrderEventDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderExtDTO toDTO(TblTOrderExt entity) {
        var dto = new OrderExtDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static PnlDTO toDTO(TblTPnl entity) {
        var dto = new PnlDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static ParamDTO toDTO(TblSParam entity) {
        var dto = new ParamDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static ProductDTO toDTO(TblSProduct entity) {
        var dto = new ProductDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static PnlSettlementDTO toDTO(TblTPnlSettlement entity) {
        var dto = new PnlSettlementDTO();
        copyProperties(entity, dto);
        return dto;
    }
}
