package net.willshouse.planner;

import java.io.IOException;

/**
 * Created by whartsell on 1/15/15.
 */
public class PredictedTakeoffFanSpeed extends PerformanceChart {
    ChartSeries ptfs;

    public PredictedTakeoffFanSpeed() throws IOException {
        super("PredictedTakeoffFanSpeed.json");
        ptfs = toChartSeries("PTFS", "x", "data");
    }

    public double calculate(double temperature) {
        double fanSpeed = ptfs.interpolateY(temperature);
        return ChartUtils.roundToSignificantFigures(fanSpeed, 3);
    }

}
