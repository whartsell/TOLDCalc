package net.willshouse.planner.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by whartsell on 1/23/15.
 */
public class Weather {

    private final StringProperty name;
    private final IntegerProperty temperature;
    private final IntegerProperty headWind;
    private final IntegerProperty crossWind;
    private final IntegerProperty pressureAltitude;


    public Weather() {
        name = new SimpleStringProperty();
        temperature = new SimpleIntegerProperty();
        headWind = new SimpleIntegerProperty();
        crossWind = new SimpleIntegerProperty();
        pressureAltitude = new SimpleIntegerProperty();

    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getTemperature() {
        return temperature.get();
    }

    public void setTemperature(int temperature) {
        this.temperature.set(temperature);
    }

    public IntegerProperty temperatureProperty() {
        return temperature;
    }

    public int getHeadWind() {
        return headWind.get();
    }

    public void setHeadWind(int headWind) {
        this.headWind.set(headWind);
    }

    public IntegerProperty headWindProperty() {
        return headWind;
    }

    public int getCrossWind() {
        return crossWind.get();
    }

    public void setCrossWind(int crossWind) {
        this.crossWind.set(crossWind);
    }

    public IntegerProperty crossWindProperty() {
        return crossWind;
    }

    public int getPressureAltitude() {
        return pressureAltitude.get();
    }

    public void setPressureAltitude(int pressureAltitude) {
        this.pressureAltitude.set(pressureAltitude);
    }

    public IntegerProperty pressureAltitudeProperty() {
        return pressureAltitude;
    }

    public boolean isValid() {
        return true;
    }
}
