package net.willshouse.planner;

import junit.framework.TestCase;

public class TakeOffSpeedTest extends TestCase {
    //sample problem given T.O 1a-10a-1-1 A2.4
    public void testSampleProblem() throws Exception {
        double grossWeight = 40000;
        int flapSetting = 7;
        double takeOffSpeed = new TakeOffSpeed().calculate(grossWeight, flapSetting);
        assertEquals(137d, takeOffSpeed);
    }
}