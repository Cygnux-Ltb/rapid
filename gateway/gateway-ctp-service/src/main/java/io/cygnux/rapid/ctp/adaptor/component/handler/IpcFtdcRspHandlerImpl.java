package io.cygnux.rapid.ctp.adaptor.component.handler;

import io.mercury.common.character.Charsets;
import io.mercury.common.concurrent.disruptor.RingEventbus;
import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.serialization.specific.BytesSerializer;
import io.mercury.transport.zmq.ZmqConfigurator;
import io.mercury.transport.zmq.ZmqPublisher;
import io.cygnux.rapid.ctp.adaptor.FtdcRspHandlerImpl;
import io.cygnux.gateway.ctp.event.FtdcRspEvent;
import io.cygnux.rapid.core.account.Account;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static io.mercury.transport.zmq.ZmqUtil.toTopicWithBytes;
import static io.cygnux.rapid.core.adapter.Adapter.publishPath;

@Component("ipcFtdcHandler")
public class IpcFtdcRspHandlerImpl extends FtdcRspHandlerImpl {

    private static final Logger log = Log4j2LoggerFactory.getLogger(IpcFtdcRspHandlerImpl.class);

    @Resource(name = "account")
    private Account account;

    private final BytesSerializer<FtdcRspEvent> serializer = event -> event.toJson().getBytes(Charsets.UTF8);

    private ZmqPublisher<FtdcRspEvent> inboundPublisher;

    private byte[] mdTopic;
    private byte[] tdTopic;

    private RingEventbus<FtdcRspEvent> inboundEventbus;

    @PostConstruct
    public void init() {
//        this.inboundEventbus = RingEventbus
//                .singleProducer(InboundEvent.EVENT_FACTORY)
//                .size(128)
//                .name("ftdc-eventbus")
//                .waitStrategy(Yielding.get())
//                .process(processor);
        this.inboundPublisher = ZmqConfigurator.ipc(publishPath)
                .createPublisher(serializer);
        this.mdTopic = toTopicWithBytes(account.getTopicByMd());
        this.tdTopic = toTopicWithBytes(account.getTopicByTd());

    }

    /**
     * 未支持的事件
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onUnsupported(FtdcRspEvent event) {
        log.error("NOTE UNSUPPORTED EVENT >>>> {}", event);
    }

    /**
     * 通信连接断开
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFrontDisconnected(FtdcRspEvent event) {
        switch (event.getFrontDisconnected().Source) {
            case MD -> inboundPublisher.publish(mdTopic, event);
            case TD -> inboundPublisher.publish(tdTopic, event);
        }
    }

    /**
     * 心跳超时警告
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onHeartBeatWarning(FtdcRspEvent event) {
        switch (event.getHeartBeatWarning().Source) {
            case MD -> inboundPublisher.publish(mdTopic, event);
            case TD -> inboundPublisher.publish(tdTopic, event);
        }
    }

    /**
     * 错误应答
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onRspError(FtdcRspEvent event) {
        switch (event.getRspError().Source) {
            case MD -> inboundPublisher.publish(mdTopic, event);
            case TD -> inboundPublisher.publish(tdTopic, event);
        }
    }

    /**
     * 用户登录响应
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onRspUserLogin(FtdcRspEvent event) {
        switch (event.getRspUserLogin().Source) {
            case MD -> inboundPublisher.publish(mdTopic, event);
            case TD -> inboundPublisher.publish(tdTopic, event);
        }
    }

    /**
     * 用户登出请求(响应)
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onUserLogout(FtdcRspEvent event) {
        switch (event.getUserLogout().Source) {
            case MD -> inboundPublisher.publish(mdTopic, event);
            case TD -> inboundPublisher.publish(tdTopic, event);
        }
    }

    /**
     * 行情处理
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcDepthMarketData(FtdcRspEvent event) {
        inboundPublisher.publish(mdTopic, event);
    }

    /**
     * 指定的合约
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcSpecificInstrument(FtdcRspEvent event) {
        inboundPublisher.publish(mdTopic, event);
    }

    /**
     * 返回报单错误
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcInputOrder(FtdcRspEvent event) {
        inboundPublisher.publish(tdTopic, event);
    }

    /**
     * 返回撤单提交错误
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcInputOrderAction(FtdcRspEvent event) {
        inboundPublisher.publish(tdTopic, event);
    }

    /**
     * 合约状态
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcInstrumentStatus(FtdcRspEvent event) {
        inboundPublisher.publish(mdTopic, event);
        inboundPublisher.publish(tdTopic, event);
    }

    /**
     * 持仓信息
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcInvestorPosition(FtdcRspEvent event) {
        inboundPublisher.publish(tdTopic, event);
    }

    /**
     * 报单推送
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcOrder(FtdcRspEvent event) {
        inboundPublisher.publish(tdTopic, event);
    }

    /**
     * 返回撤单错误
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcOrderAction(FtdcRspEvent event) {
        inboundPublisher.publish(tdTopic, event);
    }

    /**
     * 成交推送
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcTrade(FtdcRspEvent event) {
        inboundPublisher.publish(tdTopic, event);
    }

    /**
     * 账户信息(余额)
     *
     * @param event FtdcRspEvent
     */
    @Override
    protected void onFtdcTradingAccount(FtdcRspEvent event) {
        inboundPublisher.publish(tdTopic, event);
    }

}
