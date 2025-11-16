package io.cygnux.rapid.core.adaptor.event;

import io.mercury.common.serialization.Copyable;
import io.mercury.serialization.json.JsonBean;
import io.cygnux.rapid.core.shared.enums.MarketDataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 订阅行情
 */
@Getter
@Setter
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribeMarketData extends JsonBean implements Copyable<SubscribeMarketData> {

    private int accountId;
    private String investorId;
    private MarketDataType type;
    private List<String> instrumentCodes;
    private String recvAddr;

    @Override
    public void copyOf(SubscribeMarketData source) {
        this.accountId = source.accountId;
        this.type = source.type;
        this.instrumentCodes = source.instrumentCodes;
        this.recvAddr = source.recvAddr;
    }

}