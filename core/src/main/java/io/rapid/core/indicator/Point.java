package io.rapid.core.indicator;

import io.mercury.common.sequence.OrderedObject;

public sealed interface Point extends OrderedObject<Point>
        permits AbstractPoint {

    int getIndex();

}
