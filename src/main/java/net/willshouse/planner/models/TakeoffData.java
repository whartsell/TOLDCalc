package net.willshouse.planner.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import net.willshouse.planner.charts.SingleEngineROC;

import java.io.IOException;

/**
 * Created by whartsell on 1/23/15.
 */
public class TakeoffData {
    private final DoubleProperty PTFSField;
    private final IntegerProperty singleEngineROC;

    private Weather weather;
    private Aircraft aircraft;


    public TakeoffData() {
        PTFSField = new SimpleDoubleProperty();
        singleEngineROC = new SimpleIntegerProperty();
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public void calculateSingleEngineROC() throws IOException {
        if (aircraft.isValid() && weather.isValid()) {
            singleEngineROC.setValue(new SingleEngineROC().calculate(weather.getTemperature()
                    , weather.getPressureAltitude(), aircraft.getGrossWeight()
                    , aircraft.getDragIndex(), aircraft.getMaxThrust()
            ));
        }

    }


}
