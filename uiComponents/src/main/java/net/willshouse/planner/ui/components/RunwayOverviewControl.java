package net.willshouse.planner.ui.components;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import net.willshouse.planner.models.Runway;

import java.io.IOException;

/**
 * Created by whartsell on 1/23/15.
 */
public class RunwayOverviewControl extends AnchorPane {

    @FXML
    private TextField headingField;

    @FXML
    private TextField lengthField;

    @FXML
    private TextField elevationField;

    @FXML
    private TextField rcrField;

    private Runway runway;


    public RunwayOverviewControl() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/RunwayOverview.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        fxmlLoader.setClassLoader(getClass().getClassLoader());
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setRunway(Runway runway) {
        this.runway = runway;
        headingField.setText(Integer.toString(runway.getHeading()));
        lengthField.setText(Integer.toString(runway.getHeading()));
        elevationField.setText(Integer.toString(runway.getElevation()));
        rcrField.setText(Integer.toString(runway.getRcr()));
    }

    @FXML
    private void initialize() {
        headingField.textProperty().addListener(
                (obs, oldval, newval) -> runway.setHeading(Integer.parseInt(newval))
        );

        lengthField.textProperty().addListener(
                (obs, oldval, newval) -> runway.setLength(Integer.parseInt(newval))
        );

        elevationField.textProperty().addListener(
                (obs, oldval, newval) -> runway.setElevation(Integer.parseInt(newval))
        );

        rcrField.textProperty().addListener(
                (obs, oldval, newval) -> runway.setRcr(Integer.parseInt(newval))
        );

    }

}
