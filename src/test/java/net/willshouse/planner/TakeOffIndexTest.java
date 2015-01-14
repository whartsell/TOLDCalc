package net.willshouse.planner;

import junit.framework.TestCase;

public class TakeOffIndexTest extends TestCase {
    //sample problem given in T.O 1a-10a-1-1 A2-6
    public void testSampleProblem() throws Exception {
        double runwayTemperature = 15;
        double pressureAltitude = 1000;
        boolean maxThrust = true;
        double takeOffIndex = new TakeOffIndex().calculate(runwayTemperature, pressureAltitude,
                maxThrust);
        assertEquals(9.6d, takeOffIndex);

    }

    public void testHighIndexSigFig() throws Exception {
        double runwayTemperature = -27;
        double pressureAltitude = 0;
        boolean maxThrust = true;
        double takeOffIndex = new TakeOffIndex().calculate(runwayTemperature, pressureAltitude,
                maxThrust);
        assertEquals(10.9d, takeOffIndex);

    }
}