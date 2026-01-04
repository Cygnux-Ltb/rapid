package io.cygnux.rapid.engine.trader;

import io.cygnux.rapid.core.position.Position;
import io.cygnux.rapid.core.trade.TradeCommand;
import io.cygnux.rapid.core.trade.TradeCommandProducer;
import org.springframework.stereotype.Component;

import static io.cygnux.rapid.core.event.enums.TrdAction.CLOSE;
import static io.cygnux.rapid.core.event.enums.TrdAction.INVALID;
import static io.cygnux.rapid.core.event.enums.TrdAction.OPEN;
import static java.lang.Math.abs;

/**
 * 交易指令生产者简单实现
 */
@Component
public class TradeCommandProducerImpl implements TradeCommandProducer {

    @Override
    public TradeCommand toTradeCommand(int targetQty, Position position) {
        /// 获取当前净持仓
        int netQty = position.getNetQty();
        /// 获取目标交易方向
        /// var direction = targetQty > 0 ? LONG : SHORT;

        /// 净持仓为0
        if (netQty == 0) {
            /// 净持仓为0, 目标数量大于0, 开多单
            if (targetQty > 0)
                return new TradeCommand(OPEN, targetQty, INVALID, 0);
            /// 净持仓为0, 目标数量小于0, 开空单
            else
                return new TradeCommand(INVALID, 0, OPEN, abs(targetQty));
        }
        /// 净持仓大于0, 当前为多头持仓
        else if (netQty > 0) {
            /// 当前为多头持仓, 目标为继续开多单
            if (targetQty > 0)
                return new TradeCommand(OPEN, targetQty, INVALID, 0);
            /// 当前为多头持仓, 目标为反向开空单
            else {
                /// 获取目标数量绝对值
                int absTargetQty = abs(targetQty);
                /// 净持仓大于等于目标数量, 全部设置为平多
                if (netQty >= absTargetQty)
                    return new TradeCommand(CLOSE, absTargetQty, INVALID, 0);
                /// 净持仓小于目标数量, 净持仓全部平多, 差值设置为开空
                else
                    return new TradeCommand(CLOSE, netQty, OPEN, absTargetQty - netQty);
            }
        }
        /// 净持仓小于0, 当前为空头持仓
        else {
            /// 当前为空头持仓, 目标为继续开空单
            if (targetQty < 0)
                return new TradeCommand(INVALID, 0, OPEN, abs(targetQty));
            /// 当前为空头持仓, 目标为反向开多单
            else {
                /// 获取净持仓绝对值
                int absNetQty = abs(netQty);
                /// 净持仓大于等于目标数量, 全部设置为平空
                if (absNetQty >= targetQty)
                    return new TradeCommand(INVALID, 0, CLOSE, abs(targetQty));
                /// 净持仓小于目标数量, 净持仓全部平空, 差值设置为开多
                else
                    return new TradeCommand(OPEN, targetQty - absNetQty, CLOSE, absNetQty);
            }

        }
    }

}
