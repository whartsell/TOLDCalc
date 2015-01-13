package net.willshouse.planner;

import java.io.IOException;
import java.util.Map;

/**
 * Created by whartsell on 1/8/2015.
 */
public class CriticalFieldLength extends PerformanceChart {
    private Map<Double, ChartSeries> grossWeights;
    private Map<Double, ChartSeries> winds;
    private Map<Double, ChartSeries> fieldLengths;


    public CriticalFieldLength() throws IOException {
        super("CriticalFieldLength.json");
        grossWeights = toChartSeriesMap("grossWeights");
        winds = toChartSeriesMap("winds");
        fieldLengths = toChartSeriesMap("fieldLengths");
    }

    public double calculate(double takeoffIndex, double grossWeight, double wind, double rcr, int flaps,
                            boolean speedBrakesOpen, boolean useBestSEROC) {
        //todo head/tailwinds are not symmetrical as expected need to add new charts for tailwind
        double flapsDistanceModifier = 1.0d;
        double speedBrakesDistanceModifier = 1.7d;
        double criticalFieldLength;

        switch (flaps) {
            case 0: {
                if (useBestSEROC) {
                    flapsDistanceModifier = 1.22d;
                } else flapsDistanceModifier = 1.07d;
            }
            break;

            case 7: {
                if (useBestSEROC) {
                    flapsDistanceModifier = 1.16d;
                } else flapsDistanceModifier = 1.0d;
            }
            break;

            default:
                throw new IllegalArgumentException("Flaps must be 0 or 7");
        }
        if (speedBrakesOpen) speedBrakesDistanceModifier = 1.0d;


        double interpolatedGrossWeight = interpolateBetweenSeries(grossWeights, takeoffIndex, grossWeight);
        double interpolatedWinds = interpolateBetweenSeries(winds, wind, interpolatedGrossWeight);


        criticalFieldLength = interpolateBetweenSeries(fieldLengths, rcr, interpolatedWinds) * 100 *
                flapsDistanceModifier * speedBrakesDistanceModifier;

        return criticalFieldLength;
    }

}
