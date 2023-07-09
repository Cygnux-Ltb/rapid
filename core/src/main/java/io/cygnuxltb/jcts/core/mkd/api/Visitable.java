package io.cygnuxltb.jcts.core.mkd.api;

import io.horizon.market.data.api.Visitor;

public interface Visitable {

    <R, I> R accept(Visitor<R, I> visitor, I input);

}
