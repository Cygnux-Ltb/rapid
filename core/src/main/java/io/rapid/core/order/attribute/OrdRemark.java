package io.rapid.core.order.attribute;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.serialization.specific.JsonSerializable;
import io.mercury.common.util.StringSupport;
import io.mercury.serialization.json.JsonWriter;
import org.eclipse.collections.api.list.MutableList;

import javax.annotation.Nonnull;

public final class OrdRemark implements JsonSerializable {

    private final MutableList<String> remarks = MutableLists.newFastList();

    public void add(@Nonnull String remark) {
        if (StringSupport.nonEmpty(remark))
            remarks.add(remark);
    }

    @Override
    public String toString() {
        return JsonWriter.toJson(remarks);
    }

    @Override
    @Nonnull
    public String toJson() {
        return toString();
    }

}
