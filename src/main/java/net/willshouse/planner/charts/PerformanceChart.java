package net.willshouse.planner.charts;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.ArrayUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by whartsell on 1/7/15.
 */
public abstract class PerformanceChart {

    private ObjectMapper mapper;
    private JsonNode rootNode;

    protected PerformanceChart(String resourceName) throws IOException {
        mapper = new ObjectMapper();
        rootNode = mapper.readTree(this.getClass().getClassLoader().getResource(resourceName));
    }

    public static double interpolateBetweenSeries(Map<Double, ChartSeries> seriesMap, double x, double interpolatedSeries
    ) {
        ChartSeries interpolatedChartSeries;
        ArrayList<Double> seriesKeys = new ArrayList<Double>();
        ArrayList<Double> seriesValues = new ArrayList<Double>();

        for (Double key : seriesMap.keySet()) {
            seriesKeys.add(key);
            seriesValues.add(seriesMap.get(key).interpolateY(x));
        }

        interpolatedChartSeries = new ChartSeries(
                ArrayUtils.toPrimitive(seriesKeys.toArray(new Double[seriesKeys.size()])),
                ArrayUtils.toPrimitive(seriesValues.toArray(new Double[seriesValues.size()])));


        return interpolatedChartSeries.interpolateY(interpolatedSeries);
    }

    protected Map<Double, ChartSeries> toChartSeriesMap(String fieldName) throws IOException {
        Map<Double, ChartSeries> results = new TreeMap<Double, ChartSeries>();
        double[] xValues = jsonToDoubleArray(rootNode.get(fieldName).get("x"));
        Iterator<String> iter = rootNode.get(fieldName).get("data").fieldNames();
        while (iter.hasNext()) {
            String name = iter.next();
            results.put(Double.parseDouble(name), new ChartSeries(xValues, jsonToDoubleArray(rootNode.get(fieldName).get("data").get(name))));
        }
        return results;
    }

    //todo need to add min/max val extraction from json
    protected ChartSeries toChartSeries(String seriesName, String xField, String yField) throws IOException {
        return new ChartSeries(jsonToDoubleArray(rootNode.get(seriesName).get(xField)),
                jsonToDoubleArray((rootNode.get(seriesName).get(yField))));
    }

    protected double[] jsonToDoubleArray(JsonNode jsonNode) throws IOException {
        return ArrayUtils.toPrimitive(mapper.readValue(jsonNode.toString(), Double[].class));
    }

}
