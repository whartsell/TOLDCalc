package net.willshouse.planner.ui.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import net.willshouse.planner.models.Aircraft;
import net.willshouse.planner.models.Runway;
import net.willshouse.planner.models.TakeoffData;
import net.willshouse.planner.models.Weather;

import java.io.IOException;

/**
 * Created by whartsell on 4/10/15.
 */
public class TakeoffDataControl extends AnchorPane {
    private TakeoffData takeoffData;
    @FXML
    private Label seroc;
    @FXML
    private Label criticalFieldLength;
    @FXML
    private Label fanSpeed;
    @FXML
    private Label accelerationCheckSpeed;
    @FXML
    private Label continuationSpeed;
    @FXML
    private Label refusalSpeed;
    @FXML
    private Label rotationSpeed;
    @FXML
    private Label takeoffSpeed;

    public TakeoffDataControl(Aircraft aircraft, Weather weather, Runway runway) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/TakeoffData.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.setClassLoader(getClass().getClassLoader());
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        takeoffData = new TakeoffData();
        takeoffData.setAircraft(aircraft);
        takeoffData.setWeather(weather);
        takeoffData.setRunway(runway);

    }

    @FXML
    private void initialize() {


    }

    @FXML
    private void handleCalculate() {

        try {
            takeoffData.calculateSingleEngineROC();
            seroc.setText(Integer.toString(takeoffData.getSingleEngineROC()));

        } catch (Exception e) {
            e.printStackTrace();
            seroc.setText("Error");
        }

        //todo should have all methods that depend on to index to validate it first
        try {
            takeoffData.calculateTakeOffIndex();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            takeoffData.calculateCriticalFieldLength();
            criticalFieldLength.setText(Integer.toString(takeoffData.getCriticalFieldLength()));

        } catch (Exception e) {
            e.printStackTrace();
            criticalFieldLength.setText("Error");
        }

        try {
            takeoffData.calculatePredictedTakeoffFanSpeed();
            fanSpeed.setText(Double.toString(takeoffData.getPTFSField()));

        } catch (Exception e) {
            fanSpeed.setText("Error");
        }

        try {
            takeoffData.calculateAccelerationCheckSpeed();
            accelerationCheckSpeed.setText(Integer.toString(takeoffData.getAccelerationCheckSpeed()) + "kts @ 15 Sec");

        } catch (Exception e) {
            e.printStackTrace();
            accelerationCheckSpeed.setText("Error");
        }

        try {
            takeoffData.calculateContinuationSpeed();
            continuationSpeed.setText(Integer.toString(takeoffData.getContinuationSpeed()));

        } catch (Exception e) {
            e.printStackTrace();
            continuationSpeed.setText("Error");

        }

        try {
            takeoffData.calculateRefusalSpeed();
            refusalSpeed.setText(Integer.toString(takeoffData.getRefusalSpeed()));

        } catch (Exception e) {
            e.printStackTrace();
            refusalSpeed.setText("Error");

        }

        try {
            takeoffData.calculateTakeoffSpeed();
            rotationSpeed.setText(Integer.toString(takeoffData.getTakeoffSpeed() - 10));
            takeoffSpeed.setText(Integer.toString(takeoffData.getTakeoffSpeed()));
        } catch (Exception e) {
            e.printStackTrace();
            rotationSpeed.setText("Error");
            takeoffSpeed.setText("Error");
        }
    }
}
