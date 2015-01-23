package net.willshouse.planner;

import junit.framework.TestCase;
import net.willshouse.planner.charts.PredictedTakeoffFanSpeed;

public class PredictedTakeoffFanSpeedTest extends TestCase {

    public void testCalculate() throws Exception {
        double ptfs = new PredictedTakeoffFanSpeed().calculate(15);
        assertEquals(82.7d, ptfs);
    }
}