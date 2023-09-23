package io.cygnuxltb.jcts.core.order.attr;

import io.mercury.common.collections.MutableLists;
import io.mercury.common.serialization.specific.JsonSerializable;
import io.mercury.common.util.StringSupport;
import io.mercury.serialization.json.JsonWrapper;
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
        return JsonWrapper.toJson(remarks);
    }

    @Override
    @Nonnull
    public String toJson() {
        return toString();
    }

}
