package io.rapid.adaptor.ctp.gateway.util;

import io.mercury.common.lang.exception.NativeLibraryException;
import org.slf4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;

import static io.mercury.common.log4j2.Log4j2LoggerFactory.getLogger;
import static io.mercury.common.sys.SysProperties.JAVA_LIBRARY_PATH;
import static io.mercury.common.sys.SysProperties.OS_NAME;
import static java.lang.System.load;
import static java.lang.System.loadLibrary;

public final class NativeLibraryManager {

    private static final Logger log = getLogger(NativeLibraryManager.class);

    private static final AtomicBoolean isLoaded = new AtomicBoolean(false);

    public static void tryLoad() throws NativeLibraryException {
        if (isLoaded.compareAndSet(false, true)) {
            try {
                log.info("Trying to load library !!!");
                // 根据操作系统选择加载不同库文件
                if (OS_NAME.toLowerCase().startsWith("windows")) {
                    log.info("Copy win64 library file to java.library.path -> {}", JAVA_LIBRARY_PATH);
                    // TODO 复制DLL文件到LIBRARY_PATH目录
                    // 加载[.dll]文件
                    //////////////////////////////// thostapi_wrap.dll
                    loadLibrary("thostapi_wrap");
                    log.info("System.loadLibrary() -> thostapi_wrap.dll");
                    //////////////////////////////// thostmduserapi_se.dll
                    loadLibrary("thostmduserapi_se");
                    log.info("System.loadLibrary() -> thostmduserapi_se.dll");
                    //////////////////////////////// thosttraderapi_se.dll
                    loadLibrary("thosttraderapi_se");
                    log.info("System.loadLibrary() -> thosttraderapi_se.dll");
                } else if (OS_NAME.toLowerCase().startsWith("linux")) {
                    log.info("Copy linux64 library file to java.library.path -> {}", JAVA_LIBRARY_PATH);
                    // TODO 复制SO文件到LIBRARY_PATH目录
                    // 加载[.so]文件
                    //////////////////////////////// libthostapi_wrap.so
                    load("/usr/lib/ctp_6.3.13/libthostapi_wrap.so");
                    log.info("System.load() -> /usr/lib/libthostapi_wrap.so");
                    //////////////////////////////// libthostmduserapi_se.so
                    load("/usr/lib/ctp_6.3.13/libthostmduserapi_se.so");
                    log.info("System.load() -> /usr/lib/libthostmduserapi_se.so");
                    //////////////////////////////// libthosttraderapi_se.so
                    load("/usr/lib/ctp_6.3.13/libthosttraderapi_se.so");
                    log.info("System.load() -> /usr/lib/libthosttraderapi_se.so");
                } else {
                    log.error("Unsupported OS -> {}", OS_NAME);
                    throw new UnsupportedOperationException("Unsupported operating system : " + OS_NAME);
                }
                log.info("Load library success by OS -> {}", OS_NAME);
            } catch (UnsatisfiedLinkError | SecurityException | NullPointerException e) {
                isLoaded.set(false);
                log.error("Load library failure by OS -> {}", OS_NAME);
                throw new NativeLibraryException("Load native library failure, Throw by -> " + e.getClass(), e);
            }
        } else
            log.warn("Library already loaded, CtpGateway::loadNativeLibrary() cannot be called repeatedly by FtdcGateway");
    }

}
