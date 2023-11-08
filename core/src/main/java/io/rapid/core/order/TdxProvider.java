package io.rapid.core.order;

import org.apache.avro.generic.GenericEnumSymbol;

public interface TdxProvider<T extends GenericEnumSymbol<T>> {

    T getProtocolValue();

}
