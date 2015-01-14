package net.willshouse.planner;

import junit.framework.TestCase;

public class SingleEngineROCTest extends TestCase {
    //sample problem given in T.O 1a-10a-1-1 A2-5
    public void testSampleProblem() throws Exception {
        double runwayTemperature = 15;
        double pressureAltitude = 1000;
        double grossWeight = 40000;
        double dragIndex = -0.65;
        boolean maxThrust = true;
        double singleEngineROC = new SingleEngineROC().calculate(runwayTemperature, pressureAltitude, grossWeight,
                dragIndex, maxThrust);
        assertEquals(200d, singleEngineROC);


    }
}