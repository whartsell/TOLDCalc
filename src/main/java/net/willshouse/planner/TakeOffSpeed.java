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

    public double calculate(double grossWeight, int flapSetting) {

        double takeOffSpeed = 0;
        switch (flapSetting) {

            case 0:
                takeOffSpeed = takeOffSpeeds.get("Flaps 0").interpolateY(grossWeight);
                break;
            case 7:
                takeOffSpeed = takeOffSpeeds.get("Flaps 7").interpolateY(grossWeight);
                break;

            default:
                throw new IllegalArgumentException("Flaps must be set to 0 or 7");
        }

        return takeOffSpeed;
    }
}
