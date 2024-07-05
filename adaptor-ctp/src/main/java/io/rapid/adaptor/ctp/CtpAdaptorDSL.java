package io.rapid.adaptor.ctp;

import io.mercury.common.concurrent.ring.RingEventbus.MpRingEventbus;
import io.rapid.adaptor.ctp.gateway.event.FtdcEvent;
import io.rapid.core.account.Account;
import io.rapid.core.adaptor.Adaptor;
import io.rapid.core.adaptor.ConnectionMode;
import jakarta.annotation.Nonnull;
import org.slf4j.Logger;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;

/**
 * CTP Adaptor, 用于连接上期CTP柜台
 *
 * @author yellow013
 */
public class AdvCtpAdaptor extends CtpAdaptor {

    private static final Logger log = getLogger(AdvCtpAdaptor.class);

    /**
     * 传入InboundScheduler实现, 由构造函数在内部转换为MPSC队列缓冲区
     *
     * @param account  Account
     * @param config   CtpConfig
     * @param mode     ConnectionMode
     * @param eventbus RingEventbus<FtdcEvent>
     */
    protected AdvCtpAdaptor(@Nonnull Account account,
                            @Nonnull CtpConfig config,
                            @Nonnull ConnectionMode mode,
                            @Nonnull MpRingEventbus<FtdcEvent> eventbus) {
        super(account, config, mode, eventbus);
    }


    public static DSL dsl() {
        return new DSL();
    }


    public static class DSL {

        private Account account;
        private CtpConfig config;
        private ConnectionMode mode = ConnectionMode.FULL;

        public void account(Account account) {
            this.account = account;
        }

        public void config(CtpConfig config) {
            this.config = config;
        }

        public void mode(ConnectionMode mode) {
            this.mode = mode;
        }

        public Adaptor build(MpRingEventbus<FtdcEvent> eventbus) {
            return new CtpAdaptor(account, config, mode, eventbus);
        }

    }

}
