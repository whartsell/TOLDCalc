package net.willshouse.planner.models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import net.willshouse.planner.charts.*;

import java.io.IOException;

/**
 * Created by whartsell on 1/23/15.
 */
public class TakeoffData {
    private final DoubleProperty PTFSField;
    private final IntegerProperty singleEngineROC;
    private final DoubleProperty takeOffIndex;
    private final IntegerProperty criticalFieldLength;
    private final IntegerProperty accelerationCheckSpeed;
    private final IntegerProperty refusalSpeed;
    private final IntegerProperty continuationSpeed;
    private final IntegerProperty takeoffSpeed;

    private Weather weather;
    private Aircraft aircraft;
    private Runway runway;


    public TakeoffData() {
        PTFSField = new SimpleDoubleProperty();
        singleEngineROC = new SimpleIntegerProperty();
        takeOffIndex = new SimpleDoubleProperty();
        criticalFieldLength = new SimpleIntegerProperty();
        accelerationCheckSpeed = new SimpleIntegerProperty();
        refusalSpeed = new SimpleIntegerProperty();
        continuationSpeed = new SimpleIntegerProperty();
        takeoffSpeed = new SimpleIntegerProperty();
    }


    public void calculateCriticalFieldLength() throws IOException {
        criticalFieldLength.setValue(new CriticalFieldLength().calculate(takeOffIndex.get(), aircraft.getGrossWeight(),
                weather.getHeadWind(), runway.getRcr(), aircraft.getFlapSetting(), aircraft.getSpeedBrakes(), false
        ));
    }

    public void calculatePredictedTakeoffFanSpeed() throws IOException {
        PTFSField.setValue(new PredictedTakeoffFanSpeed().calculate(weather.getTemperature()));
    }

    public void calculateSingleEngineROC() throws IOException {
        singleEngineROC.setValue(new SingleEngineROC().calculate(weather.getTemperature()
                , weather.getPressureAltitude(), aircraft.getGrossWeight()
                , aircraft.getDragIndex(), aircraft.getMaxThrust()
            ));
    }

    public void calculateTakeOffIndex() throws IOException {
        takeOffIndex.setValue(new TakeOffIndex().calculate(weather.getTemperature(),
                weather.getPressureAltitude(), aircraft.getMaxThrust()));
        System.out.println("TO Index:" + takeOffIndex.get());
    }

    public void calculateAccelerationCheckSpeed() throws IOException {
        accelerationCheckSpeed.setValue(new AccelerationCheck().calculate(takeOffIndex.get(),
                aircraft.getGrossWeight(), 15, weather.getHeadWind(), 0));
    }

    public void calculateContinuationSpeed() throws IOException {
        continuationSpeed.setValue(new ContinuationSpeed().calculate(aircraft.getGrossWeight(),
                takeOffIndex.get(), runway.getLength(), aircraft.getFlapSetting(), weather.getHeadWind(), false));
    }

    public void calculateRefusalSpeed() throws IOException {
        refusalSpeed.setValue(new RefusalSpeed().calculate(weather.getPressureAltitude(), weather.getTemperature(),
                runway.getLength(), takeOffIndex.get(), aircraft.getGrossWeight(), weather.getHeadWind(),
                runway.getRcr(), aircraft.getSpeedBrakes()));
    }

    public void calculateTakeoffSpeed() throws IOException {
        takeoffSpeed.setValue(new TakeOffSpeed().calculate(aircraft.getGrossWeight(), aircraft.getFlapSetting()));
    }

    public double getPTFSField() {
        return PTFSField.get();
    }

    public void setPTFSField(double PTFSField) {
        this.PTFSField.set(PTFSField);
    }

    public DoubleProperty PTFSFieldProperty() {
        return PTFSField;
    }

    public int getSingleEngineROC() {
        return singleEngineROC.get();
    }

    public void setSingleEngineROC(int singleEngineROC) {
        this.singleEngineROC.set(singleEngineROC);
    }

    public IntegerProperty singleEngineROCProperty() {
        return singleEngineROC;
    }

    public double getTakeOffIndex() {
        return takeOffIndex.get();
    }

    public void setTakeOffIndex(double takeOffIndex) {
        this.takeOffIndex.set(takeOffIndex);
    }

    public DoubleProperty takeOffIndexProperty() {
        return takeOffIndex;
    }

    public int getCriticalFieldLength() {
        return criticalFieldLength.get();
    }

    public void setCriticalFieldLength(int criticalFieldLength) {
        this.criticalFieldLength.set(criticalFieldLength);
    }

    public IntegerProperty criticalFieldLengthProperty() {
        return criticalFieldLength;
    }

    public int getAccelerationCheckSpeed() {
        return accelerationCheckSpeed.get();
    }

    public void setAccelerationCheckSpeed(int accelerationCheckSpeed) {
        this.accelerationCheckSpeed.set(accelerationCheckSpeed);
    }

    public IntegerProperty accelerationCheckSpeedProperty() {
        return accelerationCheckSpeed;
    }

    public int getRefusalSpeed() {
        return refusalSpeed.get();
    }

    public void setRefusalSpeed(int refusalSpeed) {
        this.refusalSpeed.set(refusalSpeed);
    }

    public IntegerProperty refusalSpeedProperty() {
        return refusalSpeed;
    }

    public int getContinuationSpeed() {
        return continuationSpeed.get();
    }

    public void setContinuationSpeed(int continuationSpeed) {
        this.continuationSpeed.set(continuationSpeed);
    }

    public IntegerProperty continuationSpeedProperty() {
        return continuationSpeed;
    }

    public int getTakeoffSpeed() {
        return takeoffSpeed.get();
    }

    public void setTakeoffSpeed(int takeoffSpeed) {
        this.takeoffSpeed.set(takeoffSpeed);
    }

    public IntegerProperty takeoffSpeedProperty() {
        return takeoffSpeed;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    public Runway getRunway() {
        return runway;
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
    }
}
