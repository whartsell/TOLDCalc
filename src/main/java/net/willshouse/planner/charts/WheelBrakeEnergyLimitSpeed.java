package net.willshouse.planner.charts;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whartsell on 1/12/15.
 */
public class WheelBrakeEnergyLimitSpeed extends PerformanceChart {

    Map<Double, ChartSeries> step2;
    Map<String, ChartSeries> step3;
    private Map<Double, ChartSeries> step1;

    public WheelBrakeEnergyLimitSpeed() throws IOException {
        super("WheelBrakeEnergyLimitSpeed.json");
        step3 = new HashMap<String, ChartSeries>();
        step1 = toChartSeriesMap("Step1");
        step2 = toChartSeriesMap("Step2");
        step3.put("Closed", toChartSeries("Step3", "x", "dataClosed"));
        step3.put("Open", toChartSeries("Step3", "x", "dataOpened"));
    }

    public double calculate(double pressureAlt, double temperature, double wind, double grossWeight, boolean speedBrakesOpened) {

        double step1FofX = interpolateBetweenSeries(step1, temperature, pressureAlt);
        double step2FofX = interpolateBetweenSeries(step2, step1FofX, grossWeight);
        double step3FofX;
        double limitSpeed;
        int sigFigs = 2;
        if (speedBrakesOpened) {
            step3FofX = step3.get("Open").interpolateY(step2FofX);
        } else {
            step3FofX = step3.get("Closed").interpolateY(step2FofX);
        }

        if (step3FofX > 190) step3FofX = 190;
        limitSpeed = step3FofX + wind;
        if (limitSpeed >= 100)
            sigFigs = 3;
        return ChartUtils.roundToSignificantFigures(limitSpeed, sigFigs);
    }
}
