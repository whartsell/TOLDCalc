package net.willshouse.planner.models;

import javafx.beans.property.*;

import java.util.function.Predicate;

/**
 * Created by whartsell on 1/20/15.
 */
public class Aircraft extends Model {
    public static Predicate<Object> validGrossWeight = Aircraft::validateGrossWeight;
    public static Predicate<Object> validDragIndex = Aircraft::validateDragIndex;
    private final StringProperty name;
    private final IntegerProperty grossWeight;
    private final DoubleProperty dragIndex;
    private final IntegerProperty flapSetting;
    private final BooleanProperty maxThrust;

    public Aircraft(String name) {
        this.dragIndex = new SimpleDoubleProperty(0d);
        this.grossWeight = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty(name);
        this.flapSetting = new SimpleIntegerProperty(7);
        maxThrust = new SimpleBooleanProperty();
    }

    public static boolean validateGrossWeight(Object value) {
        return isBetween(value, 30000, 50000);

    }

    public static boolean validateDragIndex(Object dI) {
        return isBetween(dI, -4d, 7d);
    }



    public int getFlapSetting() {
        return flapSetting.get();
    }

    public void setFlapSetting(int flapSetting) {
        this.flapSetting.set(flapSetting);
    }

    public IntegerProperty flapSettingProperty() {
        return flapSetting;
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

    public int getGrossWeight() {
        return grossWeight.get();
    }

    public void setGrossWeight(int grossWeight) {

        this.grossWeight.set(grossWeight);
    }

    public IntegerProperty grossWeightProperty() {
        return grossWeight;
    }

    public double getDragIndex() {
        return dragIndex.get();
    }

    public void setDragIndex(double dragIndex) {
        this.dragIndex.set(dragIndex);
    }

    public DoubleProperty dragIndexProperty() {
        return dragIndex;
    }

    public boolean getMaxThrust() {
        return maxThrust.get();
    }

    public void setMaxThrust(boolean maxThrust) {
        this.maxThrust.set(maxThrust);
    }

    public BooleanProperty maxThrustProperty() {
        return maxThrust;
    }

    public boolean isValid() {
        return true;
    }
}
