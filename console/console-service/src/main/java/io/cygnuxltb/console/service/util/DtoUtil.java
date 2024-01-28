package io.cygnuxltb.console.service.util;

import io.cygnuxltb.console.persistence.entity.MkdBarEntity;
import io.cygnuxltb.console.persistence.entity.MkdInstrumentEntity;
import io.cygnuxltb.console.persistence.entity.MkdInstrumentSettlementEntity;
import io.cygnuxltb.console.persistence.entity.SysParamEntity;
import io.cygnuxltb.console.persistence.entity.SysProductEntity;
import io.cygnuxltb.console.persistence.entity.TrdAccountEntity;
import io.cygnuxltb.console.persistence.entity.TrdOrderEntity;
import io.cygnuxltb.console.persistence.entity.TrdOrderEventEntity;
import io.cygnuxltb.console.persistence.entity.TrdOrderExtEntity;
import io.cygnuxltb.console.persistence.entity.TrdPnlEntity;
import io.cygnuxltb.console.persistence.entity.TrdPnlSettlementEntity;
import io.cygnuxltb.console.persistence.entity.TrdStrategyEntity;
import io.cygnuxltb.protocol.http.response.dto.AccountDTO;
import io.cygnuxltb.protocol.http.response.dto.BarDTO;
import io.cygnuxltb.protocol.http.response.dto.InstrumentDTO;
import io.cygnuxltb.protocol.http.response.dto.InstrumentSettlementDTO;
import io.cygnuxltb.protocol.http.response.dto.OrderDTO;
import io.cygnuxltb.protocol.http.response.dto.OrderEventDTO;
import io.cygnuxltb.protocol.http.response.dto.OrderExtDTO;
import io.cygnuxltb.protocol.http.response.dto.ParamDTO;
import io.cygnuxltb.protocol.http.response.dto.PnlDTO;
import io.cygnuxltb.protocol.http.response.dto.PnlSettlementDTO;
import io.cygnuxltb.protocol.http.response.dto.ProductDTO;
import io.cygnuxltb.protocol.http.response.dto.StrategyDTO;

import static org.springframework.beans.BeanUtils.copyProperties;

public class DtoUtil {

    public static BarDTO toDto(MkdBarEntity entity) {
        var dto = new BarDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static AccountDTO toDto(TrdAccountEntity entity) {
        var dto = new AccountDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static InstrumentDTO toDto(MkdInstrumentEntity entity) {
        var dto = new InstrumentDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static InstrumentSettlementDTO toDto(MkdInstrumentSettlementEntity entity) {
        var dto = new InstrumentSettlementDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static StrategyDTO toDto(TrdStrategyEntity entity) {
        var dto = new StrategyDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderDTO toDto(TrdOrderEntity entity) {
        var dto = new OrderDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderEventDTO toDto(TrdOrderEventEntity entity) {
        var dto = new OrderEventDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderExtDTO toDto(TrdOrderExtEntity entity) {
        var dto = new OrderExtDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static PnlDTO toDto(TrdPnlEntity entity) {
        var dto = new PnlDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static ParamDTO toDto(SysParamEntity entity) {
        var dto = new ParamDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static ProductDTO toDto(SysProductEntity entity) {
        var dto = new ProductDTO();
        copyProperties(entity, dto);
        return dto;
    }

    public static PnlSettlementDTO toDto(TrdPnlSettlementEntity entity) {
        var dto = new PnlSettlementDTO();
        copyProperties(entity, dto);
        return dto;
    }
}
