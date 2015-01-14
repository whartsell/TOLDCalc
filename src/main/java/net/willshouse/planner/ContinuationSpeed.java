package net.willshouse.planner;

import java.io.IOException;
import java.util.Map;

/**
 * Created by whartsell on 1/13/15.
 */
public class ContinuationSpeed extends PerformanceChart {


    private Map<Double, ChartSeries> Step1;
    private Map<Double, ChartSeries> Step2;
    private Map<Double, ChartSeries> Step3;
    private Map<Double, ChartSeries> Step4;

    public ContinuationSpeed() throws IOException {
        super("ContinuationSpeed.json");
        Step1 = toChartSeriesMap("Step1");
        Step2 = toChartSeriesMap("Step2");
        Step3 = toChartSeriesMap("Step3");
        Step4 = toChartSeriesMap("Step4");


        //todo add headwind step
        //todo add tailwind step
    }


    public double calculate(double grossWeight, double takeoffIndex, double rwyLength, int flapSetting, double wind) {
        int sigFigs = 2;
        double continuationSpeed;
        double step1FofX = interpolateBetweenSeries(Step1, takeoffIndex, rwyLength);
        double step2FofX = interpolateBetweenSeries(Step2, step1FofX, grossWeight);
        double step3FofX;
        // adjust the offset in the graph. Step 3 x is shifted 10 units to the right.  if we are <10 we use the min
        // continuation speed of 70kts which x = 0
        //there is a case where we can be <10 and flaps0 will bring us back into the positive..thinking about it
        if (step2FofX > 99) throw new IllegalArgumentException("invalid performance for runway/weight/takeoff index");
        else if (step2FofX < 10 && flapSetting == 7) step3FofX = 0;
        else if (step2FofX < 10 && flapSetting == 0) step3FofX = 18;
        else {
            step2FofX = step2FofX - 10;
            // after a certain point only flaps 7 are allowed on the chart
            //todo create a custom exception for configuration/performance cases where we violate the chart constraints
            if (step2FofX > 70 && flapSetting < 7)
                throw new IllegalArgumentException("flaps must be set to 7 for this configuration");
            step3FofX = interpolateBetweenSeries(Step3, flapSetting, step2FofX);
        }

        //do wind here
        continuationSpeed = interpolateBetweenSeries(Step4, wind, step3FofX);


        //convert it from units to kts
        continuationSpeed = continuationSpeed + 70;
        if (continuationSpeed >= 100)
            sigFigs = 3;

        return ChartUtils.roundToSignificantFigures(continuationSpeed, sigFigs);
    }
}
