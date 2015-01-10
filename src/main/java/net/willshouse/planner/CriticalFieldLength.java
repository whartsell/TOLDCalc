package net.willshouse.planner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by whartsell on 1/8/2015.
 */
public class CriticalFieldLength extends PerformanceChart {
    private Map<Double, ChartSeries> grossWeights;
    private Map<Double,ChartSeries> winds;
    private Map<Double,ChartSeries> fieldLengths;


    public CriticalFieldLength() throws IOException {
        super("CriticalFieldLength.json");
        grossWeights = toChartSeriesMap("grossWeights");
        winds = toChartSeriesMap("winds");
        fieldLengths = toChartSeriesMap("fieldLengths");
    }

    public Map<String, Double> calculate(double takeoffIndex, double grossWeight,double wind,double rcr) {
        //todo for tailwinds need to take the absolute value of the wind component get the results
        //then take the delta between x and f(x) and negate it
        double absWind = Math.abs(wind);
        Map<String, Double> results = new HashMap<String, Double>();
        double interpolatedGrossWeight = interpolateBetweenSeries(grossWeights, takeoffIndex,grossWeight);
        double interpolatedWinds = interpolateBetweenSeries(winds,wind,interpolatedGrossWeight);
        double criticalFieldLength = interpolateBetweenSeries(fieldLengths,rcr,interpolatedWinds);
        results.put("Critical Field Length",criticalFieldLength*100);
        return results;
    }

}
