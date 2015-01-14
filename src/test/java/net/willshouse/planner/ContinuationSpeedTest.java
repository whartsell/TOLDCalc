package net.willshouse.planner;

import junit.framework.TestCase;

public class ContinuationSpeedTest extends TestCase {

    public void testSampleProblem() throws Exception {
        double takeoffIndex = 9.6;
        double rwyLength = 6000;
        double grossWeight = 40000;
        int flaps = 0;
        double wind = 10;
        double slope = .01;
        double continuationSpeed = new ContinuationSpeed().calculate(grossWeight, takeoffIndex, rwyLength, flaps,
                wind, false);

        assertEquals(115d, continuationSpeed);

    }
}