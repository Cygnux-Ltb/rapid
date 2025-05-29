package io.rapid.core.order;

public interface OrderRefKeeper {

    /**
     * 提供简单内存实现的管理器实例
     */
    OrderRefKeeper IN_HEAP_INSTANCE = new OrderRefKeeperInHeap();

    void binding(String orderRef, long ordSysId);

    long getOrdSysId(String orderRef);

    String getOrderRef(long ordSysId) throws OrderRefNotFoundException;

    int nextOrderRef();


}
