package io.rapid.adaptor.ctp.gateway;

import io.mercury.common.log4j2.Log4j2LoggerFactory;
import io.mercury.common.sys.SysProperties;
import org.rationalityfrontline.jctp.jctpJNI;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

import static io.mercury.common.sys.SysProperties.OS_NAME;

/**
 * 本地库文件加载器
 */
public final class NativeLibraryLoader {

    private static final Logger log = Log4j2LoggerFactory.getLogger(NativeLibraryLoader.class);

    private static final AtomicBoolean IS_LOADED = new AtomicBoolean(false);

    /**
     * Try Load Or Crash
     */
    public static void tryLoadOrCrash() {
        if (IS_LOADED.compareAndSet(false, true)) {
            try {
                log.info("############## Trying to load library !!! ##############");
                // 根据操作系统选择加载不同库文件
                if (SysProperties.isWindows() || SysProperties.isLinux())
                    log.info("The current os environment is {}", OS_NAME);
                else {
                    log.error("Unsupported OS -> {}", OS_NAME);
                    throw new UnsupportedOperationException("Unsupported OS : " + OS_NAME);
                }
                if (jctpJNI.libraryLoaded())
                    log.info("Load library succeeded by OS -> {}", OS_NAME);
                else {
                    log.error("Load library failed by OS -> {}", OS_NAME);
                    throw new IllegalStateException("Load library failed by OS : " + OS_NAME);
                }
            } catch (Exception e) {
                log.error("Load native library failure, Message -> {}", e.getMessage(), e);
                log.error("CTP native library file loading error, System must exit, Status -1");
                System.exit(-1);
            }
        } else
            log.warn("Library already loaded, jctpJNI::libraryLoaded() cannot be called repeatedly");
    }

    public static void main(String[] args) {
        NativeLibraryLoader.tryLoadOrCrash();
    }

}
