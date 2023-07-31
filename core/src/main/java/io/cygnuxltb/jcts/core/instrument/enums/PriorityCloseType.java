package io.cygnuxltb.jcts.core.instrument.attr;

import java.time.Duration;
import java.util.concurrent.SynchronousQueue;

/**
 * 优先平仓类型枚举<br>
 * <p>
 * 上海期货交易所会调整平今仓和平昨仓的手续费<br>
 * 动态调整优先平仓类型可节约交易成本
 *
 * @author yellow013
 */
public enum PriorityCloseType {

    // 无
    NONE,

    // 优先平昨仓
    YESTERDAY,

    // 优先平今仓
    TODAY;

    public static void main(String[] args) throws InterruptedException {

        var queue = new SynchronousQueue<String>();

        Thread.ofVirtual().start(() -> {
            try {
                Thread.sleep(Duration.ofSeconds(20));
                queue.put("done");
            } catch (InterruptedException ignored) {
            }
        });
        String msg = queue.take();
        System.out.println(msg);

    }

}