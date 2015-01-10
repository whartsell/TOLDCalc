package net.willshouse.planner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whartsell on 1/7/15.
 */
public class SingleEngineROC extends PerformanceChart {

    private Map<Double, ChartSeries> pressureAltitudes;
    private Map<Double, ChartSeries> grossWeights;
    private Map<Double, ChartSeries> guideLines;

    public SingleEngineROC() throws IOException {

        super("SingleEngineROC.json");
        pressureAltitudes = toChartSeriesMap("pressureAltitudes");
        grossWeights = toChartSeriesMap("grossWeights");
        guideLines = toChartSeriesMap("guidelines");
    }

    public Map<String, Double> calculate(double temperature, double pressureAltitude, double grossWeight, double dragIndex) {
        Map<String, Double> results = new HashMap<String, Double>();
        if (pressureAltitude < 0) pressureAltitude = 0;
        double interpolatedAltitude = interpolateBetweenSeries(pressureAltitudes, temperature,pressureAltitude);
        double interpolatedGrossWeight = interpolateBetweenSeries(grossWeights, interpolatedAltitude,grossWeight);
        double interpolatedGuideLines = interpolateBetweenSeries(guideLines, dragIndex * 4,interpolatedGrossWeight);
        int SingleEngineROC = (int) (100 * (interpolatedGuideLines / 2 - 25));
        results.put("Single Engine Rate of Climb (Gear Down)", (double) SingleEngineROC + 100);
        results.put("Single Engine Rate of Clime (Gear Down + Flaps 7)", (double) SingleEngineROC);
        return results;
    }
}
