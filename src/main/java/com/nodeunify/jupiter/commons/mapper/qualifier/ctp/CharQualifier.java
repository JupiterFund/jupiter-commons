package com.nodeunify.jupiter.commons.mapper.qualifier.ctp;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.nodeunify.jupiter.trader.ctp.v1.DirectionEnum.Direction;
import com.nodeunify.jupiter.trader.ctp.v1.HedgeFlagEnum.HedgeFlag;
import com.nodeunify.jupiter.trader.ctp.v1.OffsetFlagEnum.OffsetFlag;
import com.nodeunify.jupiter.trader.ctp.v1.Order.ContingentCondition;
import com.nodeunify.jupiter.trader.ctp.v1.Order.ForceCloseReason;
import com.nodeunify.jupiter.trader.ctp.v1.Order.OrderPriceType;
import com.nodeunify.jupiter.trader.ctp.v1.Order.TimeCondition;
import com.nodeunify.jupiter.trader.ctp.v1.Order.VolumeCondition;
import com.nodeunify.jupiter.trader.ctp.v1.OrderAction.ActionFlag;

import org.mapstruct.Qualifier;

public class CharQualifier {

    // @formatter:off

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyDirectionBack { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyOffsetFlagBack { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyHedgeFlagBack { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyOrderPriceTypeBack { }
    
    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyTimeConditionBack { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyVolumeConditionBack { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyContingentConditionBack { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyForceCloseReasonBack { }

    @Qualifier
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.SOURCE)
    public @interface IdentifyActionFlagBack { }

    // @formatter:on

    @IdentifyDirectionBack
    public char identifyDirectionBack(Direction direction) {
        if (direction == Direction.BUY) {
            return (char) '0';
        } 
        if (direction == Direction.SELL) {
            return (char) '1';
        }
        return Character.MIN_VALUE;
    }

    @IdentifyOffsetFlagBack
    public String identifyOffsetFlagBack(OffsetFlag offsetFlag) {
        if (offsetFlag == OffsetFlag.OPEN) {
            return "0";
        } 
        if (offsetFlag == OffsetFlag.CLOSE) {
            return "1";
        }
        if (offsetFlag == OffsetFlag.FORCE_CLOSE) {
            return "2";
        } 
        if (offsetFlag == OffsetFlag.CLOSE_TODAY) {
            return "3";
        }
        if (offsetFlag == OffsetFlag.CLOSE_YESTERDAY) {
            return "4";
        } 
        if (offsetFlag == OffsetFlag.FORCE_OFF) {
            return "5";
        }
        if (offsetFlag == OffsetFlag.LOCAL_FORCE_CLOSE) {
            return "6";
        } 
        return null;
    }

    @IdentifyHedgeFlagBack
    public String identifyHedgeFlagBack(HedgeFlag hedgeFlag) {
        if (hedgeFlag == HedgeFlag.SPECULATION) {
            return "1";
        } 
        if (hedgeFlag == HedgeFlag.ARBITRAGE) {
            return "2";
        }
        if (hedgeFlag == HedgeFlag.HEDGE) {
            return "3";
        }
        return null;
    }

    @IdentifyOrderPriceTypeBack
    public char identifyOrderPriceTypeBack(OrderPriceType orderPriceType) {
        if (orderPriceType == OrderPriceType.ANY_PRICE) {
            return (char) '1';
        } 
        if (orderPriceType == OrderPriceType.LIMIT_PRICE) {
            return (char) '2';
        }
        if (orderPriceType == OrderPriceType.BEST_PRICE) {
            return (char) '3';
        }
        if (orderPriceType == OrderPriceType.LAST_PRICE) {
            return (char) '4';
        }
        if (orderPriceType == OrderPriceType.LAST_PRICE_PLUS_ONE_TICKS) {
            return (char) '5';
        }
        if (orderPriceType == OrderPriceType.LAST_PRICE_PLUS_TWO_TICKS) {
            return (char) '6';
        }
        if (orderPriceType == OrderPriceType.LAST_PRICE_PLUS_THREE_TICKS) {
            return (char) '7';
        }
        if (orderPriceType == OrderPriceType.ASK_PRICE_1) {
            return (char) '8';
        }
        if (orderPriceType == OrderPriceType.ASK_PRICE_1_PLUS_ONE_TICKS) {
            return (char) '9';
        }
        if (orderPriceType == OrderPriceType.ASK_PRICE_1_PLUS_TWO_TICKS) {
            return (char) 'A';
        }
        if (orderPriceType == OrderPriceType.ASK_PRICE_1_PLUS_THREE_TICKS) {
            return (char) 'B';
        }
        if (orderPriceType == OrderPriceType.BID_PRICE_1) {
            return (char) 'C';
        }
        if (orderPriceType == OrderPriceType.BID_PRICE_1_PLUS_ONE_TICKS) {
            return (char) 'D';
        }
        if (orderPriceType == OrderPriceType.BID_PRICE_1_PLUS_TWO_TICKS) {
            return (char) 'E';
        }
        if (orderPriceType == OrderPriceType.BID_PRICE_1_PLUS_THREE_TICKS) {
            return (char) 'F';
        }
        return Character.MIN_VALUE;
    }

    @IdentifyTimeConditionBack
    public char identifyTimeConditionBack(TimeCondition timeCondition) {
        if (timeCondition == TimeCondition.IOC) {
            return (char) '1';
        } 
        if (timeCondition == TimeCondition.GFS) {
            return (char) '2';
        }
        if (timeCondition == TimeCondition.GFD) {
            return (char) '3';
        } 
        if (timeCondition == TimeCondition.GTD) {
            return (char) '4';
        }
        if (timeCondition == TimeCondition.GTC) {
            return (char) '5';
        } 
        if (timeCondition == TimeCondition.GFA) {
            return (char) '6';
        }
        return Character.MIN_VALUE;
    }

    @IdentifyVolumeConditionBack
    public char identifyVolumeConditionBack(VolumeCondition volumeCondition) {
        if (volumeCondition == VolumeCondition.AV) {
            return (char) '1';
        } 
        if (volumeCondition == VolumeCondition.MV) {
            return (char) '2';
        }
        if (volumeCondition == VolumeCondition.CV) {
            return (char) '3';
        }
        return Character.MIN_VALUE;
    }

    @IdentifyContingentConditionBack
    public char identifyContingentConditionBack(ContingentCondition contingentCondition) {
        if (contingentCondition == ContingentCondition.IMMEDIATELY) {
            return (char) '1';
        } 
        if (contingentCondition == ContingentCondition.TOUCH) {
            return (char) '2';
        }
        if (contingentCondition == ContingentCondition.TOUCH_PROFIT) {
            return (char) '3';
        } 
        if (contingentCondition == ContingentCondition.PARKED_ORDER) {
            return (char) '4';
        }
        if (contingentCondition == ContingentCondition.LAST_PRICE_GREATER_THAN_STOP_PRICE) {
            return (char) '5';
        } 
        if (contingentCondition == ContingentCondition.LAST_PRICE_GREATER_EQUAL_STOP_PRICE) {
            return (char) '6';
        }
        if (contingentCondition == ContingentCondition.LAST_PRICE_LESSER_THAN_STOP_PRICE) {
            return (char) '7';
        } 
        if (contingentCondition == ContingentCondition.LAST_PRICE_LESSER_EQUAL_STOP_PRICE) {
            return (char) '8';
        }
        if (contingentCondition == ContingentCondition.ASK_PRICE_GREATER_THAN_STOP_PRICE) {
            return (char) '9';
        } 
        if (contingentCondition == ContingentCondition.ASK_PRICE_GREATER_EQUAL_STOP_PRICE) {
            return (char) 'A';
        }
        if (contingentCondition == ContingentCondition.ASK_PRICE_LESSER_THAN_STOP_PRICE) {
            return (char) 'B';
        } 
        if (contingentCondition == ContingentCondition.ASK_PRICE_LESSER_EQUAL_STOP_PRICE) {
            return (char) 'C';
        }
        if (contingentCondition == ContingentCondition.BID_PRICE_GREATER_THAN_STOP_PRICE) {
            return (char) 'D';
        } 
        if (contingentCondition == ContingentCondition.BID_PRICE_GREATER_EQUAL_STOP_PRICE) {
            return (char) 'E';
        }
        if (contingentCondition == ContingentCondition.BID_PRICE_LESSER_THAN_STOP_PRICE) {
            return (char) 'F';
        } 
        if (contingentCondition == ContingentCondition.BID_PRICE_LESSER_EQUAL_STOP_PRICE) {
            return (char) 'H';
        }
        return Character.MIN_VALUE;
    }

    @IdentifyForceCloseReasonBack
    public char identifyForceCloseReasonBack(ForceCloseReason forceCloseReason) {
        if (forceCloseReason == ForceCloseReason.NOT_FORCE_CLOSE) {
            return (char) '0';
        } 
        if (forceCloseReason == ForceCloseReason.LACK_DEPOSIT) {
            return (char) '1';
        }
        if (forceCloseReason == ForceCloseReason.CLIENT_OVER_POSITION_LIMIT) {
            return (char) '2';
        } 
        if (forceCloseReason == ForceCloseReason.MEMBER_OVER_POSITION_LIMIT) {
            return (char) '3';
        }
        if (forceCloseReason == ForceCloseReason.MEMBER_OVER_POSITION_LIMIT) {
            return (char) '4';
        } 
        if (forceCloseReason == ForceCloseReason.VIOLATION) {
            return (char) '5';
        }
        if (forceCloseReason == ForceCloseReason.OTHER) {
            return (char) '6';
        } 
        if (forceCloseReason == ForceCloseReason.PERSON_DELIV) {
            return (char) '7';
        }
        return Character.MIN_VALUE;
    }

    @IdentifyActionFlagBack
    public char identifyActionFlagBack(ActionFlag actionFlag) {
        if (actionFlag == ActionFlag.DELETE) {
            return (char) '0';
        } 
        if (actionFlag == ActionFlag.MODIFY) {
            return (char) '3';
        }
        return Character.MIN_VALUE;
    }

}
