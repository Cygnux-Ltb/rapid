package io.cygnuxltb.jcts.core.trader.order;

import org.apache.avro.generic.GenericEnumSymbol;

public interface TdxProvider<T extends GenericEnumSymbol<T>> {

    T getTdxValue();

}
