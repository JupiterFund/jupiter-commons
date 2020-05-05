package com.nodeunify.jupiter.commons.mapper.qualifier.ctp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.nodeunify.jupiter.trader.ctp.v1.Source;
import com.nodeunify.jupiter.trader.ctp.v1.DirectionEnum.Direction;
import com.nodeunify.jupiter.trader.ctp.v1.HedgeFlagEnum.HedgeFlag;
import com.nodeunify.jupiter.trader.ctp.v1.InvestorPosition.PosiDirection;
import com.nodeunify.jupiter.trader.ctp.v1.InvestorPosition.PositionDate;
import com.nodeunify.jupiter.trader.ctp.v1.OffsetFlagEnum.OffsetFlag;
import com.nodeunify.jupiter.trader.ctp.v1.Order.ContingentCondition;
import com.nodeunify.jupiter.trader.ctp.v1.Order.ForceCloseReason;
import com.nodeunify.jupiter.trader.ctp.v1.Order.OrderPriceType;
import com.nodeunify.jupiter.trader.ctp.v1.Order.OrderSource;
import com.nodeunify.jupiter.trader.ctp.v1.Order.OrderStatus;
import com.nodeunify.jupiter.trader.ctp.v1.Order.OrderSubmitStatus;
import com.nodeunify.jupiter.trader.ctp.v1.Order.OrderType;
import com.nodeunify.jupiter.trader.ctp.v1.Order.TimeCondition;
import com.nodeunify.jupiter.trader.ctp.v1.Order.VolumeCondition;
import com.nodeunify.jupiter.trader.ctp.v1.OrderAction.ActionFlag;
import com.nodeunify.jupiter.trader.ctp.v1.OrderAction.OrderActionStatus;
import com.nodeunify.jupiter.trader.ctp.v1.Trade.PriceSource;
import com.nodeunify.jupiter.trader.ctp.v1.Trade.TradeType;
import com.nodeunify.jupiter.trader.ctp.v1.Trade.TradingRole;

import org.mapstruct.Qualifier;

public class EnumQualifier {

    // @formatter:off
    
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyDirection { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyOffsetFlag { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyHedgeFlag { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyOrderPriceType { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyTimeCondition { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyVolumeCondition { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyContingentCondition { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyForceCloseReason { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyOrderSubmitStatus { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyOrderSource { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyOrderStatus { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyOrderType { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyActionFlag { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyOrderActionStatus { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyTradingRole { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyTradeType { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyPriceSource { }
    
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyPosiDirection { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyPositionDate { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyErrorSource { }

    // @formatter:on

    @IdentifyDirection
    public Direction identifyDirection(char c) {
        switch (c) {
            case '0':
                return Direction.BUY;

            case '1':
                return Direction.SELL;
        
            default:
                return Direction.UNRECOGNIZED;
        }
    }

    @IdentifyOffsetFlag
    public OffsetFlag identifyOffsetFlag(String s) {
        switch (s) {
            case "0":
                return OffsetFlag.OPEN;

            case "1":
                return OffsetFlag.CLOSE;

            case "2":
                return OffsetFlag.FORCE_CLOSE;

            case "3":
                return OffsetFlag.CLOSE_TODAY;

            case "4":
                return OffsetFlag.CLOSE_YESTERDAY;

            case "5":
                return OffsetFlag.FORCE_OFF;

            case "6":
                return OffsetFlag.LOCAL_FORCE_CLOSE;
        
            default:
                return OffsetFlag.UNRECOGNIZED;
        }
    }

    @IdentifyHedgeFlag
    public HedgeFlag identifyHedgeFlag(String s) {
        switch (s) {
            case "1":
                return HedgeFlag.SPECULATION;

            case "2":
                return HedgeFlag.ARBITRAGE;

            case "3":
                return HedgeFlag.HEDGE;
        
            default:
                return HedgeFlag.UNRECOGNIZED;
        }
    }

    @IdentifyOrderPriceType
    public OrderPriceType identifyOrderPriceType(char c) {
        switch (c) {
            case '1':
                return OrderPriceType.ANY_PRICE;

            case '2':
                return OrderPriceType.LIMIT_PRICE;

            case '3':
                return OrderPriceType.BEST_PRICE;

            case '4':
                return OrderPriceType.LAST_PRICE;

            case '5':
                return OrderPriceType.LAST_PRICE_PLUS_ONE_TICKS;

            case '6':
                return OrderPriceType.LAST_PRICE_PLUS_TWO_TICKS;

            case '7':
                return OrderPriceType.LAST_PRICE_PLUS_THREE_TICKS;

            case '8':
                return OrderPriceType.ASK_PRICE_1;

            case '9':
                return OrderPriceType.ASK_PRICE_1_PLUS_ONE_TICKS;

            case 'A':
                return OrderPriceType.ASK_PRICE_1_PLUS_TWO_TICKS;

            case 'B':
                return OrderPriceType.ASK_PRICE_1_PLUS_THREE_TICKS;

            case 'C':
                return OrderPriceType.BID_PRICE_1;

            case 'D':
                return OrderPriceType.BID_PRICE_1_PLUS_ONE_TICKS;

            case 'E':
                return OrderPriceType.BID_PRICE_1_PLUS_TWO_TICKS;

            case 'F':
                return OrderPriceType.BID_PRICE_1_PLUS_THREE_TICKS;
        
            default:
                return OrderPriceType.UNRECOGNIZED;
        }
    }

    @IdentifyTimeCondition
    public TimeCondition identifyTimeCondition(char c) {
        switch (c) {
            case '1':
                return TimeCondition.IOC;

            case '2':
                return TimeCondition.GFS;

            case '3':
                return TimeCondition.GFD;

            case '4':
                return TimeCondition.GTD;

            case '5':
                return TimeCondition.GTC;

            case '6':
                return TimeCondition.GFA;
        
            default:
                return TimeCondition.UNRECOGNIZED;
        }
    }

    @IdentifyVolumeCondition
    public VolumeCondition identifyVolumeCondition(char c) {
        switch (c) {
            case '1':
                return VolumeCondition.AV;

            case '2':
                return VolumeCondition.MV;

            case '3':
                return VolumeCondition.CV;
        
            default:
                return VolumeCondition.UNRECOGNIZED;
        }
    }

    @IdentifyOrderSubmitStatus
    public OrderSubmitStatus identifyOrderSubmitStatus(char c) {
        switch (c) {
            case '0':
                return OrderSubmitStatus.INSERT_SUBMITTED;

            case '1':
                return OrderSubmitStatus.CANCEL_SUBMITTED ;

            case '2':
                return OrderSubmitStatus.MODIFY_SUBMITTED;

            case '3':
                return OrderSubmitStatus.ACCEPTED;

            case '4':
                return OrderSubmitStatus.INSERT_REJECTED;

            case '5':
                return OrderSubmitStatus.CANCEL_REJECTED;

            case '6':
                return OrderSubmitStatus.MODIFY_REJECTED;
        
            default:
                return OrderSubmitStatus.UNRECOGNIZED;
        }
    }
    
    @IdentifyOrderSource
    public OrderSource identifyOrderSource(char c) {
        switch (c) {
            case '0':
                return OrderSource.PARTICIPANT;

            case '1':
                return OrderSource.ADMINISTRATOR;
        
            default:
                return OrderSource.UNRECOGNIZED;
        }
    }

    @IdentifyOrderStatus
    public OrderStatus identifyOrderStatus(char c) {
        switch (c) {
            case '0':
                return OrderStatus.ALL_TRADED;

            case '1':
                return OrderStatus.PART_TRADED_QUEUEING;

            case '2':
                return OrderStatus.PART_TRADED_NOT_QUEUEING;

            case '3':
                return OrderStatus.NO_TRADE_QUEUEING;

            case '4':
                return OrderStatus.NO_TRADE_NOT_QUEUEING;

            case '5':
                return OrderStatus.CANCELED;

            case 'a':
                return OrderStatus.UNKNOWN;

            case 'b':
                return OrderStatus.NOT_TOUCHED;

            case 'c':
                return OrderStatus.TOUCHED;
        
            default:
                return OrderStatus.UNRECOGNIZED;
        }
    }

    @IdentifyOrderType
    public OrderType identifyOrderType(char c) {
        switch (c) {
            case '0':
                return OrderType.NORMAL;

            case '1':
                return OrderType.DERIVE_FROM_QUOTE;

            case '2':
                return OrderType.DERIVE_FROM_COMBINATION;

            case '3':
                return OrderType.COMBINATION;

            case '4':
                return OrderType.CONDITIONAL_ORDER;

            case '5':
                return OrderType.SWAP;
        
            default:
                return OrderType.UNRECOGNIZED;
        }
    }

    @IdentifyContingentCondition
    public ContingentCondition identifyContingentCondition(char c) {
        switch (c) {
            case '1':
                return ContingentCondition.IMMEDIATELY;

            case '2':
                return ContingentCondition.TOUCH;

            case '3':
                return ContingentCondition.TOUCH_PROFIT;

            case '4':
                return ContingentCondition.PARKED_ORDER;

            case '5':
                return ContingentCondition.LAST_PRICE_GREATER_THAN_STOP_PRICE;

            case '6':
                return ContingentCondition.LAST_PRICE_GREATER_EQUAL_STOP_PRICE;

            case '7':
                return ContingentCondition.LAST_PRICE_LESSER_THAN_STOP_PRICE;

            case '8':
                return ContingentCondition.LAST_PRICE_LESSER_EQUAL_STOP_PRICE;

            case '9':
                return ContingentCondition.ASK_PRICE_GREATER_THAN_STOP_PRICE;

            case 'A':
                return ContingentCondition.ASK_PRICE_GREATER_EQUAL_STOP_PRICE;

            case 'B':
                return ContingentCondition.ASK_PRICE_LESSER_THAN_STOP_PRICE;

            case 'C':
                return ContingentCondition.ASK_PRICE_LESSER_EQUAL_STOP_PRICE;

            case 'D':
                return ContingentCondition.BID_PRICE_GREATER_THAN_STOP_PRICE;

            case 'E':
                return ContingentCondition.BID_PRICE_GREATER_EQUAL_STOP_PRICE;

            case 'F':
                return ContingentCondition.BID_PRICE_LESSER_THAN_STOP_PRICE;

            case 'H':
                return ContingentCondition.BID_PRICE_LESSER_EQUAL_STOP_PRICE;
        
            default:
                return ContingentCondition.UNRECOGNIZED;
        }
    }

    @IdentifyForceCloseReason
    public ForceCloseReason identifyForceCloseReason(char c) {
        switch (c) {
            case '0':
                return ForceCloseReason.NOT_FORCE_CLOSE;

            case '1':
                return ForceCloseReason.LACK_DEPOSIT;

            case '2':
                return ForceCloseReason.CLIENT_OVER_POSITION_LIMIT;

            case '3':
                return ForceCloseReason.MEMBER_OVER_POSITION_LIMIT;

            case '4':
                return ForceCloseReason.NOT_MULTIPLE;

            case '5':
                return ForceCloseReason.VIOLATION;

            case '6':
                return ForceCloseReason.OTHER;

            case '7':
                return ForceCloseReason.PERSON_DELIV;
        
            default:
                return ForceCloseReason.UNRECOGNIZED;
        }
    }

    @IdentifyActionFlag
    public ActionFlag identifyActionFlag(char c) {
        switch (c) {
            case '0':
                return ActionFlag.DELETE;

            case '3':
                return ActionFlag.MODIFY;
        
            default:
                return ActionFlag.UNRECOGNIZED;
        }
    }

    @IdentifyOrderActionStatus
    public OrderActionStatus identifyOrderActionStatus(char c) {
        switch (c) {
            case 'a':
                return OrderActionStatus.SUBMITTED;

            case 'b':
                return OrderActionStatus.ACCEPTED;

            case 'c':
                return OrderActionStatus.REJECTED;
        
            default:
                return OrderActionStatus.UNRECOGNIZED;
        }
    }

    @IdentifyTradingRole
    public TradingRole identifyTradingRole(char c) {
        switch (c) {
            case '1':
                return TradingRole.BROKER;

            case '2':
                return TradingRole.HOST;

            case '3':
                return TradingRole.Maker;
        
            default:
                return TradingRole.UNRECOGNIZED;
        }
    }

    @IdentifyTradeType
    public TradeType identifyTradeType(char c) {
        switch (c) {
            case '0':
                return TradeType.COMMON;

            case '1':
                return TradeType.OPTIONS_EXECUTION;

            case '2':
                return TradeType.OTC;

            case '3':
                return TradeType.EFP_DERIVED;

            case '4':
                return TradeType.COMBINATION_DERIVED;
        
            default:
                return TradeType.UNRECOGNIZED;
        }
    }

    @IdentifyPriceSource
    public PriceSource identifyPriceSource(char c) {
        switch (c) {
            case '0':
                return PriceSource.LAST_PRICE;

            case '1':
                return PriceSource.BUY;

            case '2':
                return PriceSource.SELL;
        
            default:
                return PriceSource.UNRECOGNIZED;
        }
    }

    @IdentifyPosiDirection
    public PosiDirection identifyPosiDirection(char c) {
        switch (c) {
            case '0':
                return PosiDirection.NET;

            case '1':
                return PosiDirection.LONG;

            case '2':
                return PosiDirection.SHORT;
        
            default:
                return PosiDirection.UNRECOGNIZED;
        }
    }

    @IdentifyPositionDate
    public PositionDate identifyPositionDate(char c) {
        switch (c) {
            case '1':
                return PositionDate.TODAY;

            case '2':
                return PositionDate.HISTORY;
        
            default:
                return PositionDate.UNRECOGNIZED;
        }
    }

    @IdentifyErrorSource
    public Source identifyErrorSource(String s) {
        switch (s) {
            case "front":
                return Source.FRONT;

            case "exchange":
                return Source.EXCHANGE;
        
            default:
                return Source.UNRECOGNIZED;
        }
    }
}
