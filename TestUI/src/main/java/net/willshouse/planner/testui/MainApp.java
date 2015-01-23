package net.willshouse.planner.testui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.willshouse.planner.models.Aircraft;
import net.willshouse.planner.models.Runway;
import net.willshouse.planner.ui.components.AircraftOverviewControl;
import net.willshouse.planner.ui.components.RunwayOverviewControl;

import java.io.IOException;

/**
 * Created by whartsell on 1/20/2015.
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Aircraft aircraft;
    private Runway runway;
    private AircraftOverviewControl aircraftOverview;
    private RunwayOverviewControl runwayOverviewControl;

    public MainApp() {
        aircraft = new Aircraft("A-10C");
        runway = new Runway();

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TOLD Calc");

        initRootLayout();

//        aircraftOverview = new AircraftOverviewControl();
//        rootLayout.setCenter(aircraftOverview);
//        aircraftOverview.setAircraft(aircraft);
//        runwayOverviewControl = new RunwayOverviewControl();
//        rootLayout.setCenter(runwayOverviewControl);
//        runwayOverviewControl.setRunway(runway);

        //showAircraftOverview();

    }

    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/net/willshouse/planner/view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAircraftOverview() {
        //            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource("/net/willshouse/planner/view/AircraftOverview.fxml"));
//            AnchorPane aircraftOverview = (AnchorPane) loader.load();


//            AircraftOverviewControl controller = loader.getController();

    }


    public Aircraft getAircraft() {
        return aircraft;
    }
}
