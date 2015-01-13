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
        takeOffIndices.put("3% Below PTFS", toChartSeries("takeOffIndices", "xPFTS", "dataPFTS"));
    }

    public double calculate(double temperature, double pressureAlt, boolean maxThrust) {
        double takeOffIndex;
        if (pressureAlt < 0) pressureAlt = 0;
        double interpolatedAltitude = interpolateBetweenSeries(pressureAltitudes, temperature,pressureAlt);
        double takeOffLookupValue = interpolatedAltitude;

        if (maxThrust) {
            takeOffIndex = takeOffIndices.get("MAX").interpolateY(takeOffLookupValue);
        } else {
            takeOffIndex = takeOffIndices.get("3% Below PTFS").interpolateY(takeOffLookupValue);
        }


        return takeOffIndex;

    }
}

