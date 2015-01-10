package net.willshouse.planner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whartsell on 1/10/15.
 */
public class TakeOffSpeed extends PerformanceChart {
    private Map<String, ChartSeries> takeOffSpeeds;

    public TakeOffSpeed() throws IOException {
        super("TakeOffSpeed.json");
        takeOffSpeeds = new HashMap<String, ChartSeries>();
        takeOffSpeeds.put("Flaps 0", toChartSeries("grossWeights", "x", "dataFlaps0"));
        takeOffSpeeds.put("Flaps 7", toChartSeries("grossWeights", "x", "dataFlaps7"));

    }

    public Map<String, Double> calculate(double grossWeight) {
        Map<String, Double> results = new HashMap<String, Double>();
        results.put("Flaps 0", takeOffSpeeds.get("Flaps 0").interpolateY(grossWeight));
        results.put("Flaps 7", takeOffSpeeds.get("Flaps 7").interpolateY(grossWeight));

        return results;
    }
}
