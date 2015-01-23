package net.willshouse.planner;

import junit.framework.TestCase;
import net.willshouse.planner.charts.ChartSeries;

public class ChartSeriesTest extends TestCase {
    double[] temperatures = {-30, -20, -10, 0, 10, 20, 30, 40, 50};
    double[] y6 = {136, 131, 125, 117, 107, 95, 80, 62, 42};
    ChartSeries test = new ChartSeries(temperatures, y6);
    double[] y4 = {145, 140, 135, 128, 120, 110, 97, 81, 64};

    public void testInterpolateY() throws Exception {
        assertEquals(42.0d, test.interpolateY(50));

    }


}