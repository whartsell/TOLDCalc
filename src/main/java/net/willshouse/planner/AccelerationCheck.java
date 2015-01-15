package net.willshouse.planner;

import java.io.IOException;
import java.util.Map;

/**
 * Created by whartsell on 1/14/15.
 */
public class AccelerationCheck extends PerformanceChart {
    Map<Double, ChartSeries> TakeoffIndex;
    Map<Double, ChartSeries> Time;

    public AccelerationCheck() throws IOException {
        super("AccelerationCheck.json");
        TakeoffIndex = toChartSeriesMap("TakeoffIndex");
        Time = toChartSeriesMap("Time");
    }

    public static int accelerationCheckTolerance(double refusalSpeed, double continuationSpeed) {
        int tolerance = (int) (refusalSpeed - continuationSpeed);
        if (tolerance < 0) throw new IllegalArgumentException("refusal speed must be greater than continuation speed");
        if (tolerance > 10) tolerance = 10;
        return tolerance;
    }

    public double calculate(double takeoffIndex, double grossWeight, int checkTime, double wind, double slope) {
        int sigFigs = 2;
        double fOfTakeoffIndex = interpolateBetweenSeries(TakeoffIndex, takeoffIndex, grossWeight);
        double fOfTime = interpolateBetweenSeries(Time, fOfTakeoffIndex, checkTime);
        if (fOfTime < 0) throw new IllegalArgumentException("time selected is too short for takeoff configuration");
        else if (fOfTime > 100)
            throw new IllegalArgumentException("time selected is too long for takeoff configuration");
        else {
            //do winds here
        }
        double accelerationCheck = fOfTime + 60;
        if (accelerationCheck >= 100)
            sigFigs = 3;
        return ChartUtils.roundToSignificantFigures(accelerationCheck, sigFigs);
    }
}
