package net.willshouse.planner;

import junit.framework.TestCase;

public class AccelerationCheckTest extends TestCase {

    public void testSampleProblem() throws Exception {
        double takeoffIndex = 9.6;
        double grossWeight = 40000;
        int checkTime = 15;
        double wind = 10;
        double slope = .01;
        double accelerationCheckSpeed = new AccelerationCheck().calculate(takeoffIndex,
                grossWeight, checkTime, wind, slope);
        assertEquals(98d, accelerationCheckSpeed);
    }

}