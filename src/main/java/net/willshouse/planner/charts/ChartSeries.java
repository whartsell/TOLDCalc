package net.willshouse.planner.charts;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.OutOfRangeException;

/**
 * Created by whartsell on 1/7/15.
 */
public class ChartSeries {

    private double[] xValues;
    private double[] yValues;
    private int minFX, maxFX;
    private boolean seriesHasConstraints;
    private PolynomialSplineFunction yFunction, xFunction;


    public ChartSeries(double[] xValues, double[] yValues, int minFX, int maxFX, boolean seriesHasConstraints) {
        this.xValues = xValues;
        this.yValues = yValues;
        this.maxFX = maxFX;
        this.minFX = minFX;
        this.seriesHasConstraints = seriesHasConstraints;
        if (this.xValues.length < 3 || this.yValues.length < 3) {
            LinearInterpolator yInterpolator = new LinearInterpolator();
            yFunction = yInterpolator.interpolate(xValues, yValues);
        } else {
            SplineInterpolator yInterpolator = new SplineInterpolator();
            yFunction = yInterpolator.interpolate(xValues, yValues);
        }
    }

    public ChartSeries(double[] xValues, double[] yValues) {
        this(xValues, yValues, 0, 0, false);
    }

    public ChartSeries(double[] xValues, double[] yValues, int minFX, int maxFX) {
        this(xValues, yValues, minFX, maxFX, true);
    }

    public double interpolateY(double x) {
        double value = yFunction.value(x);
        if (seriesHasConstraints) {
            if (value > maxFX || value < minFX) {
                throw new OutOfRangeException(value, minFX, maxFX);
            }
        }
        return value;
    }

    public double[] getxValues() {
        return xValues;
    }


    public double[] getyValues() {
        return yValues;
    }


}
