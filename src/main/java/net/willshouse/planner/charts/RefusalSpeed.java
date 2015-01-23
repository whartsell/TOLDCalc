package net.willshouse.planner.charts;

import java.io.IOException;
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

    public double calculate(double pressureAlt, double temperature, double runwayLength, double takeOffIndex, double grossWeight, double wind, double RCR, boolean speedBrakesOpened) throws IOException {
        double speedBrakesSpeedModifier = 1.0d;
        int sigFigs = 2;

        if (!speedBrakesOpened) {
            speedBrakesSpeedModifier = 0.96d;
            if (RCR < 13) speedBrakesSpeedModifier = 0.87d;
        }

        double limitSpeed = new WheelBrakeEnergyLimitSpeed().calculate(pressureAlt, temperature,
                wind, grossWeight, speedBrakesOpened);

        double step1FofX = interpolateBetweenSeries(step1, takeOffIndex, runwayLength);
        double step2FofX = interpolateBetweenSeries(step2, step1FofX, grossWeight);
        double refusalSpeed = interpolateBetweenSeries(step3, RCR, step2FofX) + 50;
        refusalSpeed = refusalSpeed * speedBrakesSpeedModifier + wind;


        if (limitSpeed < refusalSpeed) refusalSpeed = limitSpeed;

        if (refusalSpeed >= 100)
            sigFigs = 3;
        return ChartUtils.roundToSignificantFigures(refusalSpeed, sigFigs);
    }
}
