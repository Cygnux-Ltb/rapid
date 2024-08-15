package io.rapid.core.indicator;

import io.mercury.common.collections.Capacity;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.map.primitive.MutableLongObjectMap;

import javax.annotation.CheckForNull;
import javax.annotation.concurrent.NotThreadSafe;
import java.util.Optional;

import static io.mercury.common.collections.MutableLists.newFastList;
import static io.mercury.common.collections.MutableMaps.newLongObjectMap;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@NotThreadSafe
public final class PointSet<P extends Point> {

    private final MutableList<P> list;

    private final MutableLongObjectMap<P> map;

    private PointSet(Capacity capacity) {
        this.list = newFastList(capacity.size());
        this.map = newLongObjectMap(capacity.size());
    }

    /**
     * @return PointSet<P>
     */
    public static <P extends Point> PointSet<P> newEmpty() {
        return new PointSet<>(Capacity.L07_128);
    }

    /**
     * @param capacity Capacity
     * @return PointSet<P>
     */
    public static <P extends Point> PointSet<P> newEmpty(Capacity capacity) {
        return new PointSet<>(capacity);
    }

    /**
     * @param point P
     * @return boolean
     */
    public boolean add(P point) {
        long serialId = point.serialId();
        if (map.containsKey(serialId))
            return false;
        map.put(serialId, point);
        return list.add(point);
    }

    /**
     * @return int
     */
    public int size() {
        return list.size();
    }

    /**
     * @return P
     */
    public P getLast() {
        return list.getLast();
    }

    /**
     * @return P
     */
    public P getFirst() {
        return list.getFirst();
    }

    /**
     * @param index int
     * @return Optional<P>
     */
    public Optional<P> get(int index) {
        return index < list.size() ? ofNullable(list.get(index)) : empty();
    }

    /**
     * TODO 需要修改
     *
     * @param point P
     * @return Optional<P>
     */
    public Optional<P> nextOf(P point) {
        int index = point.getIndex();
        return get(++index);
    }

    /**
     * @param serialId long
     * @return P
     */
    @CheckForNull
    public P getPoint(long serialId) {
        return map.get(serialId);
    }

    /**
     * @return MutableList<P>
     */
    public MutableList<P> getPointList() {
        return list;
    }

    /**
     * @param fromIndex int
     * @param toIndex   int
     * @return MutableList<P>
     */
    public MutableList<P> getSubPointList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }

}
