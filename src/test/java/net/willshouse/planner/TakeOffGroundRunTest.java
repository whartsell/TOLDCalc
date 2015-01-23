package net.willshouse.planner;

import junit.framework.TestCase;
import net.willshouse.planner.charts.TakeOffGroundRun;

public class TakeOffGroundRunTest extends TestCase {

    //sample problem given in T.O 1a-10a-1-1 A2-7
    public void testSampleProblem() throws Exception {
        int flaps = 7;
        double takeoffIndex = 9.6;
        double grossWeight = 40000;
        double wind = 10;
        double slope = 0.01;
        double groundRun = new TakeOffGroundRun().calculate(takeoffIndex, grossWeight,
                flaps, wind, false);
        //value should be 2700 as we dont take into account slope
        assertEquals(2700d, groundRun);


    }
}