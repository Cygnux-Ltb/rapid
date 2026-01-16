package io.cygnux.rapid.infra.service.util;

import io.cygnux.rapid.infra.dto.common.GeneralParamObj;
import io.cygnux.rapid.infra.dto.resp.AccountRsp;
import io.cygnux.rapid.infra.dto.resp.AdapterParamRsp;
import io.cygnux.rapid.infra.dto.resp.AdapterRsp;
import io.cygnux.rapid.infra.dto.resp.BarRsp;
import io.cygnux.rapid.infra.dto.resp.InstrumentRsp;
import io.cygnux.rapid.infra.dto.resp.InstrumentSettlementRsp;
import io.cygnux.rapid.infra.dto.resp.OrderEventRsp;
import io.cygnux.rapid.infra.dto.resp.OrderExtRsp;
import io.cygnux.rapid.infra.dto.resp.OrderRsp;
import io.cygnux.rapid.infra.dto.resp.PnlRsp;
import io.cygnux.rapid.infra.dto.resp.PnlSettlementRsp;
import io.cygnux.rapid.infra.dto.resp.ProductRsp;
import io.cygnux.rapid.infra.dto.resp.StrategyParamRsp;
import io.cygnux.rapid.infra.dto.resp.StrategyRsp;
import io.cygnux.rapid.infra.dto.resp.SubAccountMappingRsp.SubAccountMappingObj;
import io.cygnux.rapid.infra.dto.resp.SubAccountRsp;
import io.cygnux.rapid.infra.persistence.entity.AccountEntity;
import io.cygnux.rapid.infra.persistence.entity.AccountParamEntity;
import io.cygnux.rapid.infra.persistence.entity.AdapterEntity;
import io.cygnux.rapid.infra.persistence.entity.AdapterParamEntity;
import io.cygnux.rapid.infra.persistence.entity.BarEntity;
import io.cygnux.rapid.infra.persistence.entity.InstrumentEntity;
import io.cygnux.rapid.infra.persistence.entity.InstrumentSettlementEntity;
import io.cygnux.rapid.infra.persistence.entity.OrderEntity;
import io.cygnux.rapid.infra.persistence.entity.OrderEventEntity;
import io.cygnux.rapid.infra.persistence.entity.OrderExtEntity;
import io.cygnux.rapid.infra.persistence.entity.PnlEntity;
import io.cygnux.rapid.infra.persistence.entity.PnlSettlementEntity;
import io.cygnux.rapid.infra.persistence.entity.ProductEntity;
import io.cygnux.rapid.infra.persistence.entity.StrategyEntity;
import io.cygnux.rapid.infra.persistence.entity.StrategyParamEntity;
import io.cygnux.rapid.infra.persistence.entity.SubAccountEntity;
import io.cygnux.rapid.infra.persistence.entity.SubAccountMappingEntity;
import io.cygnux.rapid.infra.persistence.entity.SubAccountParamEntity;

import static org.springframework.beans.BeanUtils.copyProperties;

public class RespConverter {

    public static AccountRsp fromEntity(AccountEntity entity) {
        var dto = new AccountRsp();
        copyProperties(entity, dto);
        return dto;
    }

    public static GeneralParamObj fromEntity(AccountParamEntity entity) {
        var dto = new GeneralParamObj();
        copyProperties(entity, dto);
        return dto;
    }

    public static SubAccountRsp fromEntity(SubAccountEntity entity) {
        var dto = new SubAccountRsp();
        copyProperties(entity, dto);
        return dto;
    }

    public static SubAccountMappingObj fromEntity(SubAccountMappingEntity entity) {
        var dto = new SubAccountMappingObj();
        copyProperties(entity, dto);
        return dto;
    }

    public static GeneralParamObj fromEntity(SubAccountParamEntity entity) {
        var dto = new GeneralParamObj();
        copyProperties(entity, dto);
        return dto;
    }

    public static AdapterParamRsp fromEntity(AdapterParamEntity entity) {
        var dto = new AdapterParamRsp();
        copyProperties(entity, dto);
        return dto;
    }

    public static AdapterRsp fromEntity(AdapterEntity entity) {
        var dto = new AdapterRsp();
        copyProperties(entity, dto);
        return dto;
    }

    public static BarRsp fromEntity(BarEntity entity) {
        var dto = new BarRsp();
        copyProperties(entity, dto);
        return dto;
    }

    public static InstrumentRsp fromEntity(InstrumentEntity entity) {
        var dto = new InstrumentRsp();
        copyProperties(entity, dto);
        return dto;
    }

    public static InstrumentSettlementRsp fromEntity(InstrumentSettlementEntity entity) {
        var dto = new InstrumentSettlementRsp();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderEventRsp fromEntity(OrderEventEntity entity) {
        var dto = new OrderEventRsp();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderExtRsp fromEntity(OrderExtEntity entity) {
        var dto = new OrderExtRsp();
        copyProperties(entity, dto);
        return dto;
    }

    public static OrderRsp fromEntity(OrderEntity entity) {
        var dto = new OrderRsp();
        copyProperties(entity, dto);
        return dto;
    }

    public static PnlRsp fromEntity(PnlEntity entity) {
        var dto = new PnlRsp();
        copyProperties(entity, dto);
        return dto;
    }

    public static PnlSettlementRsp fromEntity(PnlSettlementEntity entity) {
        var dto = new PnlSettlementRsp();
        copyProperties(entity, dto);
        return dto;
    }

    public static ProductRsp fromEntity(ProductEntity entity) {
        var dto = new ProductRsp();
        copyProperties(entity, dto);
        return dto;
    }


    public static StrategyParamRsp fromEntity(StrategyParamEntity entity) {
        var dto = new StrategyParamRsp();
        copyProperties(entity, dto);
        return dto;
    }

    public static StrategyRsp fromEntity(StrategyEntity entity) {
        var dto = new StrategyRsp();
        copyProperties(entity, dto);
        return dto;
    }


}
