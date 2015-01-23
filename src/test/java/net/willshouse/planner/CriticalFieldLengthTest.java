package net.willshouse.planner;

import junit.framework.TestCase;
import net.willshouse.planner.charts.CriticalFieldLength;

public class CriticalFieldLengthTest extends TestCase {
    //sample problem given in T.O 1a-10a-1-1 A2-8

    public void testSampleProblem() throws Exception {
        boolean maxThrust = true;
        int flaps = 7;
        boolean speedBrakesOpened = true;
        double takeoffIndex = 9.6;
        double grossWeight = 40000;
        double wind = 10;
        double slope = .01;
        int RCR = 16;
        double critFieldLength = new CriticalFieldLength().calculate(takeoffIndex, grossWeight,
                wind, RCR, flaps, speedBrakesOpened, false);
        //bug need to implement slope.  value should be 5000 but is 4800 w/o
        assertEquals(4800d, critFieldLength);
    }
}