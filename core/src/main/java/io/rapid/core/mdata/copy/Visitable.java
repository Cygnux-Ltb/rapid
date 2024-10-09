package io.rapid.core.mdata.copy;

public interface Visitable {

    <R, I> R accept(Visitor<R, I> visitor, I input);

}
