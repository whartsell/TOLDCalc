package net.willshouse.planner.testui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import net.willshouse.planner.testui.MainApp;
import net.willshouse.planner.testui.model.Aircraft;

/**
 * Created by whartsell on 1/20/15.
 */
public class AircraftOverviewController {
    @FXML
    private TextField grossWeightField;

    @FXML
    private TextField dragIndexField;

    @FXML
    private ChoiceBox<String> speedBrakesChoiceBox;

    @FXML
    private ChoiceBox<Integer> flapsChoiceBox;

    @FXML
    private ChoiceBox<String> thrustChoiceBox;

    @FXML
    private ChoiceBox<String> antiSkidChoiceBox;

    private MainApp mainApp;
    private Aircraft aircraft;

    public AircraftOverviewController() {

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;


    }

    @FXML
    private void handleSet() {
        aircraft.setGrossWeight(Integer.valueOf(grossWeightField.getText()));
        System.out.println("gross Weight: " + aircraft.getGrossWeight());
        aircraft.setDragIndex(Double.valueOf(dragIndexField.getText()));
        System.out.println("Drag Index: " + aircraft.getDragIndex());
        aircraft.setFlapSetting(flapsChoiceBox.getValue());
        System.out.println("Flaps: " + aircraft.getFlapSetting());

    }

    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
        grossWeightField.setText(Integer.toString(aircraft.getGrossWeight()));
        dragIndexField.setText(Double.toString(aircraft.getDragIndex()));

        speedBrakesChoiceBox.setValue("Open");
        flapsChoiceBox.setValue(7);
        thrustChoiceBox.setValue("Max");
        antiSkidChoiceBox.setValue("On");
    }

    @FXML
    private void initialize() {
        speedBrakesChoiceBox.getItems().add("Open");
        speedBrakesChoiceBox.getItems().add("Closed");
        flapsChoiceBox.getItems().add(0);
        flapsChoiceBox.getItems().add(7);
        thrustChoiceBox.getItems().add("Max");
        thrustChoiceBox.getItems().add("3% below PTFS");
        antiSkidChoiceBox.getItems().add("Off");
        antiSkidChoiceBox.getItems().add("On");

    }

    private void showAircraftDetails(Aircraft aircraft) {

    }
}
