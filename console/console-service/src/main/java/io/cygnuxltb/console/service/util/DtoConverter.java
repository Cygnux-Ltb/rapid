package io.cygnuxltb.console.service.util;

import io.cygnuxltb.console.persistence.entity.TblMkdBar;
import io.cygnuxltb.console.persistence.entity.TblMkdInstrument;
import io.cygnuxltb.console.persistence.entity.TblMkdInstrumentSettlement;
import io.cygnuxltb.console.persistence.entity.TblSysParam;
import io.cygnuxltb.console.persistence.entity.TblSysProduct;
import io.cygnuxltb.console.persistence.entity.TblTrdAccount;
import io.cygnuxltb.console.persistence.entity.TblTrdOrder;
import io.cygnuxltb.console.persistence.entity.TblTrdOrderEvent;
import io.cygnuxltb.console.persistence.entity.TblTrdOrderExt;
import io.cygnuxltb.console.persistence.entity.TblTrdPnl;
import io.cygnuxltb.console.persistence.entity.TblTrdPnlSettlement;
import io.cygnuxltb.console.persistence.entity.TblTrdStrategy;
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

    public static BarDTO toDTO(TblMkdBar entity) {
        var dto = new BarDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static AccountDTO toDTO(TblTrdAccount entity) {
        var dto = new AccountDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static InstrumentDTO toDTO(TblMkdInstrument entity) {
        var dto = new InstrumentDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static InstrumentSettlementDTO toDTO(TblMkdInstrumentSettlement entity) {
        var dto = new InstrumentSettlementDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static StrategyDTO toDTO(TblTrdStrategy entity) {
        var dto = new StrategyDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderDTO toDTO(TblTrdOrder entity) {
        var dto = new OrderDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderEventDTO toDTO(TblTrdOrderEvent entity) {
        var dto = new OrderEventDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderExtDTO toDTO(TblTrdOrderExt entity) {
        var dto = new OrderExtDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static PnlDTO toDTO(TblTrdPnl entity) {
        var dto = new PnlDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static ParamDTO toDTO(TblSysParam entity) {
        var dto = new ParamDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static ProductDTO toDTO(TblSysProduct entity) {
        var dto = new ProductDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static PnlSettlementDTO toDTO(TblTrdPnlSettlement entity) {
        var dto = new PnlSettlementDTO();
        copyProperties(entity, dto);
        return dto;
    }
}
