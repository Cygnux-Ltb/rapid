package io.cygnuxltb.jcts.core.mkd.impl;


import io.cygnuxltb.jcts.core.mkd.api.MarketDataIncrement;
import io.cygnuxltb.jcts.core.mkd.api.Visitor;

public class DefaultMarketDataIncrement
        extends DefaultMarketDataMessage implements MarketDataIncrement {

    private DefaultMarketDataIncrement(final Builder builder) {
        super(builder);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    @Override
    public <R, I> R accept(final Visitor<R, I> visitor, final I input) {
        return visitor.visit(this, input);
    }

    public final static class Builder extends DefaultMarketDataMessage.Builder<Builder> {

        private Builder() {
        }

        public Builder getThis() {
            return this;
        }

        @Override
        public MarketDataIncrement build() {
            return new DefaultMarketDataIncrement(this);
        }

    }

}
