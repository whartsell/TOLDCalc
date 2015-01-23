package net.willshouse.planner;

import junit.framework.TestCase;
import net.willshouse.planner.charts.ChartUtils;

public class ChartUtilsTest extends TestCase {

    public void testRoundToSignificantFigures() throws Exception {
        assertEquals(5.0d, ChartUtils.roundToSignificantFigures(5, 3));
        assertEquals(12300d, ChartUtils.roundToSignificantFigures(12345, 3));

    }

}