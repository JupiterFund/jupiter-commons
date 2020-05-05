package com.nodeunify.jupiter.commons.mapper;

import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.CharQualifier;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.CharQualifier.IdentifyActionFlagBack;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.CharQualifier.IdentifyContingentConditionBack;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.CharQualifier.IdentifyDirectionBack;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.CharQualifier.IdentifyForceCloseReasonBack;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.CharQualifier.IdentifyHedgeFlagBack;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.CharQualifier.IdentifyOffsetFlagBack;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.CharQualifier.IdentifyOrderPriceTypeBack;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.CharQualifier.IdentifyTimeConditionBack;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.CharQualifier.IdentifyVolumeConditionBack;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyActionFlag;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyContingentCondition;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyDirection;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyErrorSource;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyForceCloseReason;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyHedgeFlag;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyOffsetFlag;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyOrderActionStatus;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyOrderPriceType;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyOrderStatus;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyOrderSubmitStatus;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyPosiDirection;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyPositionDate;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyTimeCondition;
import com.nodeunify.jupiter.commons.mapper.qualifier.ctp.EnumQualifier.IdentifyVolumeCondition;
import com.nodeunify.jupiter.trader.ctp.v1.Error;
import com.nodeunify.jupiter.trader.ctp.v1.Instrument;
import com.nodeunify.jupiter.trader.ctp.v1.InvestorPosition;
import com.nodeunify.jupiter.trader.ctp.v1.Order;
import com.nodeunify.jupiter.trader.ctp.v1.OrderAction;
import com.nodeunify.jupiter.trader.ctp.v1.ResponseInfo;
import com.nodeunify.jupiter.trader.ctp.v1.Trade;
import com.nodeunify.jupiter.trader.ctp.v1.TradingAccount;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import ctp.thosttraderapi.CThostFtdcInputOrderActionField;
import ctp.thosttraderapi.CThostFtdcInputOrderField;
import ctp.thosttraderapi.CThostFtdcInstrumentField;
import ctp.thosttraderapi.CThostFtdcInvestorPositionField;
import ctp.thosttraderapi.CThostFtdcOrderActionField;
import ctp.thosttraderapi.CThostFtdcOrderField;
import ctp.thosttraderapi.CThostFtdcRspInfoField;
import ctp.thosttraderapi.CThostFtdcTradeField;
import ctp.thosttraderapi.CThostFtdcTradingAccountField;

// @formatter:off
@Mapper(uses = { EnumQualifier.class, CharQualifier.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        typeConversionPolicy = ReportingPolicy.WARN)
// @formatter:on
public interface TraderCTPMapper {

    TraderCTPMapper MAPPER = Mappers.getMapper(TraderCTPMapper.class);

    // CThostFtdcOrderField -> Order
    @Mapping(source = "orderPriceType", target = "orderPriceType", qualifiedBy = IdentifyOrderPriceType.class)
    @Mapping(source = "direction", target = "direction", qualifiedBy = IdentifyDirection.class)
    @Mapping(source = "combOffsetFlag", target = "combOffsetFlag", qualifiedBy = IdentifyOffsetFlag.class)
    @Mapping(source = "combHedgeFlag", target = "combHedgeFlag", qualifiedBy = IdentifyHedgeFlag.class)
    @Mapping(source = "timeCondition", target = "timeCondition", qualifiedBy = IdentifyTimeCondition.class)
    @Mapping(source = "volumeCondition", target = "volumeCondition", qualifiedBy = IdentifyVolumeCondition.class)
    @Mapping(source = "contingentCondition", target = "contingentCondition", qualifiedBy = IdentifyContingentCondition.class)
    @Mapping(source = "forceCloseReason", target = "forceCloseReason", qualifiedBy = IdentifyForceCloseReason.class)
    @Mapping(source = "orderSubmitStatus", target = "orderSubmitStatus", qualifiedBy = IdentifyOrderSubmitStatus.class)
    @Mapping(source = "orderSource", target = "orderSource", ignore = true)
    @Mapping(source = "orderStatus", target = "orderStatus", qualifiedBy = IdentifyOrderStatus.class)
    @Mapping(source = "orderType", target = "orderType", ignore = true)
    Order map(CThostFtdcOrderField data);

    // Workaround for the returned null char values
    // TODO: create custom presense checker or wait for the update of mapstruct
    @AfterMapping
    default void afterMapping(CThostFtdcOrderField data, @MappingTarget Order.Builder order) {
        EnumQualifier qualifier = new EnumQualifier();
        if (data.getOrderType() != '\u0000') {
            order.setOrderType(qualifier.identifyOrderType(data.getOrderType()));
        }
        if (data.getOrderSource() != '\u0000') {
            order.setOrderSource(qualifier.identifyOrderSource(data.getOrderSource()));
        }
    }

    // CThostFtdcInputOrderField -> Order
    @Mapping(source = "orderPriceType", target = "orderPriceType", qualifiedBy = IdentifyOrderPriceType.class)
    @Mapping(source = "direction", target = "direction", qualifiedBy = IdentifyDirection.class)
    @Mapping(source = "combOffsetFlag", target = "combOffsetFlag", qualifiedBy = IdentifyOffsetFlag.class)
    @Mapping(source = "combHedgeFlag", target = "combHedgeFlag", qualifiedBy = IdentifyHedgeFlag.class)
    @Mapping(source = "timeCondition", target = "timeCondition", qualifiedBy = IdentifyTimeCondition.class)
    @Mapping(source = "volumeCondition", target = "volumeCondition", qualifiedBy = IdentifyVolumeCondition.class)
    @Mapping(source = "contingentCondition", target = "contingentCondition", qualifiedBy = IdentifyContingentCondition.class)
    @Mapping(source = "forceCloseReason", target = "forceCloseReason", qualifiedBy = IdentifyForceCloseReason.class)
    Order map(CThostFtdcInputOrderField data);

    // Order -> CThostFtdcInputOrderField
    @Mapping(source = "orderPriceType", target = "orderPriceType", qualifiedBy = IdentifyOrderPriceTypeBack.class)
    @Mapping(source = "direction", target = "direction", qualifiedBy = IdentifyDirectionBack.class)
    @Mapping(source = "combOffsetFlag", target = "combOffsetFlag", qualifiedBy = IdentifyOffsetFlagBack.class)
    @Mapping(source = "combHedgeFlag", target = "combHedgeFlag", qualifiedBy = IdentifyHedgeFlagBack.class)
    @Mapping(source = "timeCondition", target = "timeCondition", qualifiedBy = IdentifyTimeConditionBack.class)
    @Mapping(source = "volumeCondition", target = "volumeCondition", qualifiedBy = IdentifyVolumeConditionBack.class)
    @Mapping(source = "contingentCondition", target = "contingentCondition", qualifiedBy = IdentifyContingentConditionBack.class)
    @Mapping(source = "forceCloseReason", target = "forceCloseReason", qualifiedBy = IdentifyForceCloseReasonBack.class)
    CThostFtdcInputOrderField map(Order data);

    // CThostFtdcOrderActionField -> OrderAction
    @Mapping(source = "actionFlag", target = "actionFlag", qualifiedBy = IdentifyActionFlag.class)
    @Mapping(source = "orderActionStatus", target = "orderActionStatus", qualifiedBy = IdentifyOrderActionStatus.class)
    OrderAction map(CThostFtdcOrderActionField data);

    // CThostFtdcInputOrderActionField -> OrderAction
    @Mapping(source = "actionFlag", target = "actionFlag", qualifiedBy = IdentifyActionFlag.class)
    // @Mapping(source = "orderActionStatus", target = "orderActionStatus", qualifiedBy = IdentifyOrderActionStatus.class)
    OrderAction map(CThostFtdcInputOrderActionField data);

    // OrderAction -> CThostFtdcInputOrderActionField
    @Mapping(source = "actionFlag", target = "actionFlag", qualifiedBy = IdentifyActionFlagBack.class)
    CThostFtdcInputOrderActionField map(OrderAction data);

    // CThostFtdcTradeField -> Trade
    @Mapping(source = "direction", target = "direction", qualifiedBy = IdentifyDirection.class)
    @Mapping(source = "offsetFlag", target = "offsetFlag", qualifiedBy = IdentifyOffsetFlag.class)
    @Mapping(source = "hedgeFlag", target = "hedgeFlag", qualifiedBy = IdentifyHedgeFlag.class)
    @Mapping(source = "tradingRole", target = "tradingRole", ignore = true)
    @Mapping(source = "tradeType", target = "tradeType", ignore = true)
    @Mapping(source = "priceSource", target = "priceSource", ignore = true)
    Trade map(CThostFtdcTradeField data);

    // Workaround for the returned null char values
    // TODO: create custom presense checker or wait for the update of mapstruct
    @AfterMapping
    default void afterMapping(CThostFtdcTradeField data, @MappingTarget Trade.Builder trade) {
        EnumQualifier qualifier = new EnumQualifier();
        if (data.getPriceSource() != '\u0000') {
            trade.setPriceSource(qualifier.identifyPriceSource(data.getPriceSource()));
        }
        if (data.getTradingRole() != '\u0000') {
            trade.setTradingRole(qualifier.identifyTradingRole(data.getTradingRole()));
        }
        if (data.getTradeType() != '\u0000') {
            trade.setTradeType(qualifier.identifyTradeType(data.getTradeType()));
        }
    }

    // CThostFtdcInstrumentField -> Instument
    // TODO: not finished yet
    @Mapping(source = "productClass", target = "productClass", ignore = true)
    @Mapping(source = "instLifePhase", target = "instLifePhase", ignore = true)
    @Mapping(source = "positionType", target = "positionType", ignore = true)
    @Mapping(source = "positionDateType", target = "positionDateType", ignore = true)
    Instrument map(CThostFtdcInstrumentField data);

    // CThostFtdcInvestorPositionField -> InvestorPosition
    @Mapping(source = "posiDirection", target = "posiDirection", qualifiedBy = IdentifyPosiDirection.class)
    @Mapping(source = "hedgeFlag", target = "hedgeFlag", qualifiedBy = IdentifyHedgeFlag.class)
    @Mapping(source = "positionDate", target = "positionDate", qualifiedBy = IdentifyPositionDate.class)
    InvestorPosition map(CThostFtdcInvestorPositionField data);

    // CThostFtdcTradingAccountField -> TradingAccount
    TradingAccount map(CThostFtdcTradingAccountField data);

    // CThostFtdcRspInfoField -> ResponseInfo
    ResponseInfo map(CThostFtdcRspInfoField data);

    // CThostFtdcInputOrderField, CThostFtdcRspInfoField -> Error
    @Mapping(source = "source", target = "source", qualifiedBy = IdentifyErrorSource.class)
    Error map(CThostFtdcInputOrderField order, CThostFtdcRspInfoField rspInfo, String source);

    // CThostFtdcInputOrderActionField, CThostFtdcRspInfoField -> Error
    @Mapping(source = "source", target = "source", qualifiedBy = IdentifyErrorSource.class)
    Error map(CThostFtdcInputOrderActionField order, CThostFtdcRspInfoField rspInfo, String source);

    // CThostFtdcOrderActionField, CThostFtdcRspInfoField -> Error
    @Mapping(source = "source", target = "source", qualifiedBy = IdentifyErrorSource.class)
    Error map(CThostFtdcOrderActionField order, CThostFtdcRspInfoField rspInfo, String source);
}
