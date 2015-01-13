package net.willshouse.planner;

import java.io.IOException;
import java.util.Map;

/**
 * Created by whartsell on 1/7/15.
 */
public class TakeOffSingleEngineROC extends PerformanceChart {

    private Map<Double, ChartSeries> pressureAltitudes;
    private Map<Double, ChartSeries> grossWeights;
    private Map<Double, ChartSeries> guideLines;

    public TakeOffSingleEngineROC() throws IOException {

        super("SingleEngineROC.json");
        pressureAltitudes = toChartSeriesMap("pressureAltitudes");
        grossWeights = toChartSeriesMap("grossWeights");
        guideLines = toChartSeriesMap("guidelines");
    }

    public double calculate(double temperature, double pressureAltitude, double grossWeight, double dragIndex, boolean maxThrust) {

        if (pressureAltitude < 0) pressureAltitude = 0;
        double interpolatedAltitude = interpolateBetweenSeries(pressureAltitudes, temperature,pressureAltitude);
        double interpolatedGrossWeight = interpolateBetweenSeries(grossWeights, interpolatedAltitude,grossWeight);
        double interpolatedGuideLines = interpolateBetweenSeries(guideLines, dragIndex * 4,interpolatedGrossWeight);
        double rateOfClimb = (int) (100 * (interpolatedGuideLines / 2 - 25));
        if (!maxThrust) rateOfClimb = rateOfClimb - 250;


        return rateOfClimb;
    }
}
