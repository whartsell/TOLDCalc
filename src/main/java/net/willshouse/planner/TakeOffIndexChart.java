package net.willshouse.planner;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by whartsell on 1/6/15.
 */
public class TakeOffIndexChart extends PerformanceChart {

    private Map<Double, ChartSeries> pressureAltitudes;
    private Map<String, ChartSeries> takeOffIndices;


    public TakeOffIndexChart() throws IOException {
        super("TakeOffIndex.json");
        pressureAltitudes = toChartSeriesMap("pressureAltitudes");
        takeOffIndices = new HashMap<String, ChartSeries>();
        takeOffIndices.put("MAX", toChartSeries("takeOffIndices", "xMax", "dataMax"));
        takeOffIndices.put("PTFS", toChartSeries("takeOffIndices", "xPFTS", "dataPFTS"));
    }

    public Map<String, Double> calculate(double temperature, double pressureAlt) {
        Map<String, Double> results = new HashMap<String, Double>();
        if (pressureAlt < 0) pressureAlt = 0;
        double interpolatedAltitude = interpolateBetweenSeries(pressureAltitudes, temperature,pressureAlt);
        double takeOffLookupValue = interpolatedAltitude;
        results.put("MAX", takeOffIndices.get("MAX").interpolateY(takeOffLookupValue));
        results.put("PTFS", takeOffIndices.get("PTFS").interpolateY(takeOffLookupValue));
        return results;

    }
}

