package io.rapid.engine.manager;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.rapid.core.adaptor.Adaptor;
import io.rapid.core.adaptor.AdaptorManager;
import org.eclipse.collections.api.map.MutableMap;
import org.eclipse.collections.api.map.primitive.MutableIntObjectMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;
import java.io.Serial;
import java.io.Serializable;
import java.util.stream.Stream;

import static io.mercury.common.collections.MutableMaps.newIntObjectMap;
import static io.mercury.common.collections.MutableMaps.newUnifiedMap;

/**
 * @author yellow013
 * @topic 存储Adaptor和Mapping关系<br>
 * <p>
 * 1.以[accountId]查找Adaptor<br>
 * TODO 修改为线程安全的管理器<br>
 * <p>
 * 如果程序运行中不修改Adaptor的引用则可以在多个线程中调用Get函数<br>
 * 如果运行中Adaptor崩溃, 重新创建Adaptor则需要重新Put<br>
 * 目前不保证这一过程的线程安全
 */
@NotThreadSafe
@Service
public final class AdaptorManagerService implements Serializable, AdaptorManager {

    @Serial
    private static final long serialVersionUID = -1199809125474119945L;

    /**
     * Logger
     */
    private static final Logger log = Log4j2LoggerFactory.getLogger(AdaptorManagerService.class);

    /**
     * 存储[Adaptor], 使用[accountId]索引
     */
    private final MutableIntObjectMap<Adaptor> mapByAccountId = newIntObjectMap();

    /**
     * 存储[Adaptor], 使用[adaptorId]索引
     */
    private final MutableMap<String, Adaptor> mapByAdaptorId = newUnifiedMap();

    // TODO 改进为异步处理方式
    public AdaptorManagerService() {
        // TODO
    }

    @Override
    public void putAdaptor(@Nonnull Adaptor... adaptors) {
        Stream.of(adaptors).forEach(adaptor -> {
            var account = adaptor.getBoundAccount();
            mapByAccountId.put(account.getAccountId(), adaptor);
            mapByAdaptorId.put(adaptor.getAdaptorId(), adaptor);
            log.info("PUT [Adaptor] to AdaptorManager, accountId==[{}], adaptorId==[{}], remark==[{}]",
                    account.getAccountId(), adaptor.getAdaptorId(), account.getRemark());
        });
    }

    @Override
    public Adaptor getAdaptor(int accountId) {
        return mapByAccountId.get(accountId);
    }

    @Override
    public Adaptor getAdaptor(String adaptorId) {
        return mapByAdaptorId.get(adaptorId);
    }
    
}
