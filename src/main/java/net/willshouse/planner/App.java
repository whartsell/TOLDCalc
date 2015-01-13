package net.willshouse.planner;


import java.io.IOException;
import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        double grossWeight = 40000;
        double flapSetting = 7;
        double toDragIndex = -0.65;
        double rwyPressureAlt = 1000;
        double temperature = 15;
        double rwyLength = 6000;
        double rwySlope = 0.01;
        double wind = 10;
        double rcr = 23;


        SingleEngineROC singleEngineROC = null;
        CriticalFieldLength criticalFieldLength = null;
        TakeOffSpeed takeOffSpeed = null;
        TakeOffGroundRun takeOffGroundRun = null;
        WheelBrakeEnergyLimitSpeed wheelBrakeEnergyLimitSpeed = null;

        try {
            TakeOffIndexChart toChart = new TakeOffIndexChart();
            singleEngineROC = new SingleEngineROC();
            criticalFieldLength = new CriticalFieldLength();
            takeOffSpeed = new TakeOffSpeed();
            takeOffGroundRun = new TakeOffGroundRun();
            wheelBrakeEnergyLimitSpeed = new WheelBrakeEnergyLimitSpeed();

            Map<String, Double> toIndex = toChart.calculate(temperature, rwyPressureAlt);
            printMap(toIndex);
            Map<String, Double> ROC = singleEngineROC.calculate(temperature, rwyPressureAlt, grossWeight, toDragIndex);
            printMap(ROC);
            Map<String, Double> cFL = criticalFieldLength.calculate(toIndex.get("MAX"), grossWeight,wind,rcr);
            printMap(cFL);
            Map<String, Double> takeOffSpeeds = takeOffSpeed.calculate(grossWeight);
            printMap(takeOffSpeeds);
            Map<String, Double> takeOffDistance = takeOffGroundRun.calculate(toIndex.get("MAX"), grossWeight, flapSetting, wind);
            printMap(takeOffDistance);
            Map<String, Double> limitSpeed = wheelBrakeEnergyLimitSpeed.calculate(rwyPressureAlt, temperature, wind, grossWeight, true);
            printMap(limitSpeed);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //test4
    }

    private static void printMap(Map<String, Double> chartResults) {
        for (String key : chartResults.keySet()) {
            System.out.printf("%s: %.1f%n", key, chartResults.get(key));
        }
    }
}
