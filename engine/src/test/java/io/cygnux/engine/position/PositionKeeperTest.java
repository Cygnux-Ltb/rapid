package io.cygnux.engine.position;

import io.cygnux.rapid.core.event.enums.TrdDirection;
import io.cygnux.rapid.core.instrument.Instrument;
import io.cygnux.rapid.core.instrument.InstrumentKeeper;
import io.cygnux.rapid.engine.position.PositionKeeper;
import org.junit.Test;


public class PositionKeeperTest {

    @Test
    public void test() {
        int subAccountId = 10;
        Instrument rb2010 = InstrumentKeeper.getInstrumentByCode("rb2010");
        PositionKeeper.setSubAccountPositionsLimit(subAccountId, rb2010, 10, 10);
        PositionKeeper.addPosition(subAccountId, rb2010, TrdDirection.LONG, 10);
        PositionKeeper.addPosition(subAccountId, rb2010, TrdDirection.SHORT, 15);
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
    public void testAddPosition() {

    }

    @Test
    public void testDump() {

    }

}
