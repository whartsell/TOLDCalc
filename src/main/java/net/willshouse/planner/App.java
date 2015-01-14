package net.willshouse.planner;


import java.io.IOException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        double grossWeight = 40000;
        int flapSetting = 7;
        double toDragIndex = -0.65;
        //double toDragIndex = 0;
        double rwyPressureAlt = 1000;
        double temperature = 15;
        double rwyLength = 6000;
        double rwySlope = 0.01;
        double wind = 10;
        double rcr = 16;
        boolean maxThrust = true;
        boolean speedBrakesOpened = true;
        boolean useBestSingleEngineRateOfClimb = false;

        try {


            double toIndex = new TakeOffIndexChart().calculate(temperature, rwyPressureAlt, maxThrust);
            System.out.printf("Takeoff Index: %.1f%n", toIndex);
            double ROC = new TakeOffSingleEngineROC().calculate(temperature, rwyPressureAlt, grossWeight, toDragIndex, maxThrust);
            System.out.printf("TakeOff Speed Single Engine Rate of Climb (gear & flaps down): %.1f%n", ROC);

            double cFL = new CriticalFieldLength().calculate(toIndex, grossWeight, wind, rcr, flapSetting, speedBrakesOpened, useBestSingleEngineRateOfClimb);
            System.out.printf("Critical Field Length: %.0f%n", cFL);
            double takeOffSpeed = new TakeOffSpeed().calculate(grossWeight, flapSetting);
            System.out.printf("Takeoff Speed: %.0f%n", takeOffSpeed);
            double takeOffDistance = new TakeOffGroundRun().calculate(toIndex, grossWeight, flapSetting, wind, useBestSingleEngineRateOfClimb);
            System.out.printf("Takeoff Distance: %.0f%n", takeOffDistance);
            double limitSpeed = new WheelBrakeEnergyLimitSpeed().calculate(rwyPressureAlt, temperature, wind, grossWeight, speedBrakesOpened);
            System.out.printf("Wheel Brake Energy Limit Speed: %.0f%n", limitSpeed);
            double refusal = new RefusalSpeed().calculate(rwyPressureAlt, temperature, rwyLength, toIndex, grossWeight, wind, rcr, speedBrakesOpened);
            System.out.printf("Refusal Speed: %.0f%n", refusal);
            double continuationSpeed = new ContinuationSpeed().calculate(grossWeight, toIndex, rwyLength, flapSetting, wind);
            System.out.printf("ContinuationSpeed: %.0f%n", continuationSpeed);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
