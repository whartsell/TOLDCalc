package net.willshouse.planner.charts;

import java.io.IOException;
import java.util.Map;

/**
 * Created by whartsell on 1/12/15.
 */
public class TakeOffGroundRun extends PerformanceChart {
    private Map<Double, ChartSeries> flaps0_grossWeights;
    private Map<Double, ChartSeries> flaps7_grossWeights;
    private Map<Double, ChartSeries> headWinds;

    public TakeOffGroundRun() throws IOException {
        super("takeOffGroundRun.json");
        flaps0_grossWeights = toChartSeriesMap("0GrossWeights");
        flaps7_grossWeights = toChartSeriesMap("7GrossWeights");
        headWinds = toChartSeriesMap("headWinds");

    }

    public double calculate(double takeoffIndex, double grossWeight, int flaps, double wind, boolean useBestSEROC) {

        //todo impliment tailwind
        //todo implement slope charts
        double interpolatedGrossWeight;
        double takeOffGroundRun;
        double bestSEROCMultiplier = 1.0d;
        int sigFigs = 2;
        switch (flaps) {
            case 0:
                interpolatedGrossWeight = interpolateBetweenSeries(flaps0_grossWeights, takeoffIndex, grossWeight);
                bestSEROCMultiplier = 1.25;
                break;
            case 7:
                interpolatedGrossWeight = interpolateBetweenSeries(flaps7_grossWeights, takeoffIndex, grossWeight);
                bestSEROCMultiplier = 1.18;
                break;

            default:
                throw new IllegalArgumentException("valid flap settings are 0,7");

        }

        takeOffGroundRun = interpolateBetweenSeries(headWinds, wind, interpolatedGrossWeight) * 100;
        if (useBestSEROC) takeOffGroundRun = takeOffGroundRun * bestSEROCMultiplier;
        if (takeOffGroundRun >= 10000)
            sigFigs = 3;

        return ChartUtils.roundToSignificantFigures(takeOffGroundRun, sigFigs);
    }
}
