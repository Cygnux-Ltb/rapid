package io.cygnuxltb.jcts.core.mkd.api;

public interface Visitable {

    <R, I> R accept(Visitor<R, I> visitor, I input);

}
