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
        int checkTime = 15;
        boolean maxThrust = true;
        boolean speedBrakesOpened = true;
        boolean useBestSingleEngineRateOfClimb = false;

        try {

            double toIndex = new TakeOffIndex().calculate(temperature, rwyPressureAlt, maxThrust);
            System.out.printf("Takeoff Index: %.1f%n", toIndex);

            double ROC = new SingleEngineROC().calculate(temperature, rwyPressureAlt, grossWeight, toDragIndex,
                    maxThrust);
            System.out.printf("TakeOff Speed Single Engine Rate of Climb (gear & flaps down): %.0fFPM%n", ROC);

            double cFL = new CriticalFieldLength().calculate(toIndex, grossWeight, wind, rcr, flapSetting,
                    speedBrakesOpened, useBestSingleEngineRateOfClimb);
            System.out.printf("Critical Field Length: %.0fft%n", cFL);

            double takeOffSpeed = new TakeOffSpeed().calculate(grossWeight, flapSetting);
            System.out.printf("Takeoff Speed: %.0fkias%n", takeOffSpeed);

            double takeOffDistance = new TakeOffGroundRun().calculate(toIndex, grossWeight, flapSetting, wind,
                    useBestSingleEngineRateOfClimb);
            System.out.printf("Takeoff Distance: %.0fft%n", takeOffDistance);

            double limitSpeed = new WheelBrakeEnergyLimitSpeed().calculate(rwyPressureAlt, temperature, wind,
                    grossWeight, speedBrakesOpened);
            System.out.printf("Wheel Brake Energy Limit Speed: %.0fkias%n", limitSpeed);

            double refusal = new RefusalSpeed().calculate(rwyPressureAlt, temperature, rwyLength, toIndex, grossWeight,
                    wind, rcr, speedBrakesOpened);
            System.out.printf("Refusal Speed: %.0fkias%n", refusal);

            double continuationSpeed = new ContinuationSpeed().calculate(grossWeight, toIndex, rwyLength, flapSetting,
                    wind, useBestSingleEngineRateOfClimb);
            System.out.printf("ContinuationSpeed: %.0fkias%n", continuationSpeed);

            double checkSpeed = new AccelerationCheck().calculate(toIndex, grossWeight, checkTime, wind, rwySlope);
            System.out.printf("Acceleration Check at %d seconds: %.0fkias with %dkias tolerance %n", checkTime,
                    checkSpeed, AccelerationCheck.accelerationCheckTolerance(refusal, continuationSpeed));

            double ptfs = new PredictedTakeoffFanSpeed().calculate(temperature);
            System.out.printf("Predicted Takeoff Fan Speed: %.1f%%%n", ptfs);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
