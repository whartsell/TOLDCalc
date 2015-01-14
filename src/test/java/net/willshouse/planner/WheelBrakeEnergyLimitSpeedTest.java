package net.willshouse.planner;

import junit.framework.TestCase;

public class WheelBrakeEnergyLimitSpeedTest extends TestCase {

    public void testSampleProblem() throws Exception {
        double temperature = 15;
        double pressureAltitude = 1000;
        double grossWeight = 40000;
        boolean speedBrakesOpen = true;
        double wind = 10;
        double wheelBrakeEnergyLimitSpeed = new WheelBrakeEnergyLimitSpeed().calculate(pressureAltitude,
                temperature, wind, grossWeight, speedBrakesOpen);
        assertTrue(wheelBrakeEnergyLimitSpeed > 190d);
        speedBrakesOpen = false;
        wheelBrakeEnergyLimitSpeed = new WheelBrakeEnergyLimitSpeed().calculate(pressureAltitude,
                temperature, wind, grossWeight, speedBrakesOpen);
        //were off by 1kt due to chart interpretation i figured we should be within 1 subdiv on graph
        //each subdivision is 2kts at the "bottom" so the range is +-1kt
        assertTrue(165d <= wheelBrakeEnergyLimitSpeed && wheelBrakeEnergyLimitSpeed <= 167d);


        ;
    }

}