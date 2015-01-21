package net.willshouse.planner.testui.view;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

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


}
