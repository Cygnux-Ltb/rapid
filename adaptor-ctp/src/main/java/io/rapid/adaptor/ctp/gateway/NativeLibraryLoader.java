package io.rapid.adaptor.ctp.gateway;

import io.mercury.common.lang.exception.NativeLibraryException;
import org.rationalityfrontline.jctp.jctpJNI;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;
import static io.mercury.common.sys.SysProperties.OS_NAME;

/**
 * 本地库文件加载器
 */
public final class NativeLibraryManager {

    private static final Logger log = getLogger(NativeLibraryManager.class);

    private static final AtomicBoolean isLoaded = new AtomicBoolean(false);

    public static void tryLoad() throws NativeLibraryException {
        
        if (isLoaded.compareAndSet(false, true)) {
            try {
                log.info("########## Trying to load library !!! ##########");
                // 根据操作系统选择加载不同库文件
                if (OS_NAME.toLowerCase().startsWith("windows")) {
                    log.info("The current environment is Windows");
                } else if (OS_NAME.toLowerCase().startsWith("linux")) {
                    log.info("The current environment is Linux");
                } else {
                    log.error("Unsupported OS -> {}", OS_NAME);
                    throw new UnsupportedOperationException("Unsupported operating system : " + OS_NAME);
                }
                if (jctpJNI.libraryLoaded()) {
                    log.info("Load library success by OS -> {}", OS_NAME);
                } else {
                    log.error("Load library failed by OS -> {}", OS_NAME);
                    throw new IllegalStateException("Load library failed by OS : " + OS_NAME);
                }
            } catch (UnsatisfiedLinkError | SecurityException | NullPointerException e) {
                isLoaded.set(false);
                throw new NativeLibraryException("Load native library failure, Exception cause -> ["
                        + e.getClass().getSimpleName() + "], OS==[" + OS_NAME + "], Message -> " + e.getMessage(), e);
            }
        } else
            log.warn("Library already loaded, jctpJNI::libraryLoaded() cannot be called repeatedly");
    }

    public static void main(String[] args) {
        try {
            NativeLibraryManager.tryLoad();
        } catch (NativeLibraryException e) {
            throw new RuntimeException(e);
        }
    }

}
