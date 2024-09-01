package io.cygnux.engine.position;

import io.rapid.core.instrument.Instrument;
import io.rapid.core.instrument.InstrumentManager;
import io.rapid.core.order.enums.TrdDirection;
import io.rapid.engine.position.PositionKeeper;
import org.junit.Test;


public class PositionKeeperTest {

    @Test
    public void test() {
        int subAccountId = 10;
        Instrument rb2010 = InstrumentManager.getInstrument("rb2010");
        PositionKeeper.setSubAccountPositionsLimit(subAccountId, rb2010, 10, 10);
        PositionKeeper.addCurrentPosition(subAccountId, rb2010, TrdDirection.Long, 10);
        PositionKeeper.addCurrentPosition(subAccountId, rb2010, TrdDirection.Short, 15);
    }

    @Test
    public void testSetPositionsLimit() {

    }

    @Test
    public void testGetPositionLimit() {

    }

    @Test
    public void testUpdatePosition() {

    }

    @Test
    public void testGetCurrentPosition() {

    }

    @Test
    public void testAddCurrentPosition() {

    }

    @Test
    public void testDump() {

    }

}
