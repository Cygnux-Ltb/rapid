package io.cygnux.rapid.infra.dto.wrap;

import io.mercury.common.codec.Envelope;
import lombok.Getter;
import org.eclipse.collections.api.map.primitive.ImmutableIntObjectMap;

import static io.mercury.common.collections.MutableMaps.newIntObjectMap;

public enum InboxTitle implements Envelope {

    UNKNOWN(-1),

    HEARTBEAT(0),

    LAST_PRICE(1),

    BAR(2),

    ORDER(3),

    METRIC_DATA(7),

    ;

    @Getter
    private final int code;

    InboxTitle(int code) {
        this.code = code;
    }

    private static final ImmutableIntObjectMap<InboxTitle> Map =
            newIntObjectMap(InboxTitle::getCode, InboxTitle.values()).toImmutable();

    public static InboxTitle checkout(int code) {
        InboxTitle value;
        if ((value = Map.get(code)) != null)
            return value;
        throw new IllegalArgumentException("checkout with code -> " + code + " is null");
    }

    @Override
    public int getVersion() {
        return 1;
    }

}
