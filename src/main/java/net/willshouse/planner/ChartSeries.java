package net.willshouse.planner;

import org.apache.commons.math3.analysis.interpolation.LinearInterpolator;
import org.apache.commons.math3.analysis.interpolation.SplineInterpolator;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;

/**
 * Created by whartsell on 1/7/15.
 */
public class ChartSeries {

    private double[] xValues;
    private double[] yValues;
    private PolynomialSplineFunction yFunction, xFunction;


    public ChartSeries(double[] xValues, double[] yValues) {
        this.xValues = xValues;
        this.yValues = yValues;
        if (this.xValues.length < 3 || this.yValues.length < 3) {
            LinearInterpolator yInterpolator = new LinearInterpolator();
            yFunction = yInterpolator.interpolate(xValues, yValues);
        } else {
            SplineInterpolator yInterpolator = new SplineInterpolator();
            yFunction = yInterpolator.interpolate(xValues, yValues);
        }


    }

    public double interpolateY(double x) {
        return yFunction.value(x);
    }

    public double[] getxValues() {
        return xValues;
    }


    public double[] getyValues() {
        return yValues;
    }


}
