package net.willshouse.planner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whartsell on 1/12/15.
 */
public class TakeOffGroundRun extends PerformanceChart {
    private Map<Double, ChartSeries> flaps0_grossWeights;
    private Map<Double, ChartSeries> flaps7_grossWeights;
    private Map<Double, ChartSeries> headWinds;

    protected TakeOffGroundRun() throws IOException {
        super("takeOffGroundRun.json");
        flaps0_grossWeights = toChartSeriesMap("0GrossWeights");
        flaps7_grossWeights = toChartSeriesMap("7GrossWeights");
        headWinds = toChartSeriesMap("headWinds");

    }

    public Map<String, Double> calculate(double takeoffIndex, double grossWeight, double flaps, double wind) {
        Map<String, Double> results = new HashMap<String, Double>();
        //todo head/tailwinds are not symmetrical as expected need to add new charts for tailwind
        double interpolatedGrossWeight;
        double absWind = Math.abs(wind);
        if (flaps == 0) {
            interpolatedGrossWeight = interpolateBetweenSeries(flaps0_grossWeights, takeoffIndex, grossWeight);
        } else if (flaps == 7) {
            interpolatedGrossWeight = interpolateBetweenSeries(flaps7_grossWeights, takeoffIndex, grossWeight);
        } else throw new IllegalArgumentException("valid flap settings are 0,7");

        results.put("debug grossWeight fx", interpolatedGrossWeight);

        double groundRun = interpolateBetweenSeries(headWinds, absWind, interpolatedGrossWeight);
        double deltaRun = Math.abs(interpolatedGrossWeight - groundRun);
        if (wind < 0) {
            groundRun = interpolatedGrossWeight + deltaRun;

        }
        results.put("debug abs", Math.abs(interpolatedGrossWeight - groundRun));
        results.put("takeOff Ground Run", groundRun * 100);


        return results;
    }
}
