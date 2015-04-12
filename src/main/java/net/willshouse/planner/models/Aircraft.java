package net.willshouse.planner.models;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.function.Predicate;

/**
 * Created by whartsell on 1/20/15.
 */
public class Aircraft extends Model {
    public static Predicate<Object> validGrossWeight = Aircraft::validateGrossWeight;
    public static Predicate<Object> validDragIndex = Aircraft::validateDragIndex;
    private final StringProperty name;
    private final BooleanProperty isValid;
    private int grossWeight;
    private double dragIndex;
    private int flapSetting;
    private boolean maxThrust;
    private boolean speedBrakes;


    public Aircraft(String name) {
//        this.dragIndex = new SimpleDoubleProperty();
//        this.grossWeight = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty(name);
        isValid = new SimpleBooleanProperty(false);
//        this.flapSetting = new SimpleIntegerProperty(7);
//        maxThrust = new SimpleBooleanProperty();
        validateAircraft();
    }

    public static boolean validateGrossWeight(Object value) {
        return isBetween(value, 30000, 50000);

    }

    public static boolean validateFlapSetting(int value) {
        if (value == 7 || value == 0) return true;
        else return false;

    }

    public static boolean validateDragIndex(Object dI) {
        return isBetween(dI, -4d, 7d);
    }


    public int getFlapSetting() {
        return flapSetting;
    }

    public void setFlapSetting(int flapSetting) {
        if (validateFlapSetting(flapSetting))
            this.flapSetting = flapSetting;
        validateAircraft();
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
        return grossWeight;
    }

    public void setGrossWeight(int grossWeight) {

        if (validateGrossWeight(grossWeight)) this.grossWeight = grossWeight;
        validateAircraft();
    }

//    public IntegerProperty grossWeightProperty() {
//        return grossWeight;
//    }

    public double getDragIndex() {
        return dragIndex;
    }

    public void setDragIndex(double dragIndex) {
        if (validateDragIndex(dragIndex))
            this.dragIndex = dragIndex;
        validateAircraft();
    }


    public boolean getMaxThrust() {
        return maxThrust;
    }

    public void setMaxThrust(boolean maxThrust) {
        this.maxThrust = maxThrust;
    }

    public boolean getSpeedBrakes() {
        return speedBrakes;
    }

    public void setSpeedBrakes(boolean speedBrakes) {
        this.speedBrakes = speedBrakes;
    }

    public boolean getIsValid() {
        return isValid.get();
    }

    public BooleanProperty isValidProperty() {
        return isValid;
    }


    //will always be true as we dont accept values unless they are valid except during initialization
    private void validateAircraft() {
        isValid.set(validateDragIndex(getDragIndex()) && validateGrossWeight(getGrossWeight()));
    }
}
