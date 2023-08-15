package io.cygnuxltb.jcts.core.mkd.copy;

public interface Visitable {

    <R, I> R accept(Visitor<R, I> visitor, I input);

}
