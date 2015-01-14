package net.willshouse.planner;

import junit.framework.TestCase;

public class RefusalSpeedTest extends TestCase {
    // A2-9
    public void testSampleProblem() throws Exception {
        boolean speedBrakesOpen = true;
        double takeoffIndex = 9.6;
        double rwyLength = 6000;
        double grossWeight = 40000;
        int rcr = 16;
        double wind = 10;
        double pressureAlt = 1000;
        double temperature = 10;
        double refusalSpeed = new RefusalSpeed().calculate(pressureAlt, temperature, rwyLength,
                takeoffIndex, grossWeight, wind, rcr, speedBrakesOpen);
        assertEquals(128d, refusalSpeed);
    }
}