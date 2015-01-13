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
        double rcr = 16;

        try {


            Map<String, Double> toIndex = new TakeOffIndexChart().calculate(temperature, rwyPressureAlt);
            printMap(toIndex);
            Map<String, Double> ROC = new SingleEngineROC().calculate(temperature, rwyPressureAlt, grossWeight, toDragIndex);
            printMap(ROC);
            Map<String, Double> cFL = new CriticalFieldLength().calculate(toIndex.get("MAX"), grossWeight, wind, rcr);
            printMap(cFL);
            Map<String, Double> takeOffSpeeds = new TakeOffSpeed().calculate(grossWeight);
            printMap(takeOffSpeeds);
            Map<String, Double> takeOffDistance = new TakeOffGroundRun().calculate(toIndex.get("MAX"), grossWeight, flapSetting, wind);
            printMap(takeOffDistance);
            Map<String, Double> limitSpeed = new WheelBrakeEnergyLimitSpeed().calculate(rwyPressureAlt, temperature, wind, grossWeight, true);
            printMap(limitSpeed);
            Map<String, Double> refusal = new RefusalSpeed().calculate(rwyPressureAlt, temperature, rwyLength, toIndex.get("MAX"), grossWeight, wind, rcr, false);
            printMap(refusal);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void printMap(Map<String, Double> chartResults) {
        for (String key : chartResults.keySet()) {
            System.out.printf("%s: %.1f%n", key, chartResults.get(key));
        }
    }
}
