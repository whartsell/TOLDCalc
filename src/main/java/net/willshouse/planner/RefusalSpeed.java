package net.willshouse.planner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whartsell on 1/12/15.
 */
public class RefusalSpeed extends PerformanceChart {
    Map<Double, ChartSeries> step1, step2, step3;

    public RefusalSpeed() throws IOException {
        super("RefusalSpeed.json");
        step1 = toChartSeriesMap("Step1");
        step2 = toChartSeriesMap("Step2");
        step3 = toChartSeriesMap("Step3");

    }

    public Map<String, Double> calculate(double pressureAlt, double temperature, double runwayLength, double takeOffIndex, double grossWeight, double wind, double RCR, boolean speedBrakesClosed) throws IOException {

        double limitSpeed = new WheelBrakeEnergyLimitSpeed().calculate(pressureAlt, temperature,
                wind, grossWeight, speedBrakesClosed).get("WheelBrake Energy Limit Speed");

        Map<String, Double> results = new HashMap<String, Double>();
        double step1FofX = interpolateBetweenSeries(step1, takeOffIndex, runwayLength);
        double step2FofX = interpolateBetweenSeries(step2, step1FofX, grossWeight);
        double refusalSpeed = interpolateBetweenSeries(step3, RCR, step2FofX) + 50 + wind;
        if (limitSpeed < refusalSpeed) refusalSpeed = limitSpeed;
        results.put("refusal speed", refusalSpeed);


        return results;
    }
}
