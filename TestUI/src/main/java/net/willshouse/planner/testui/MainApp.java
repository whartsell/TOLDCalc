package net.willshouse.planner.testui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.willshouse.planner.ui.components.AircraftOverviewControl;
import net.willshouse.planner.ui.components.model.Aircraft;

import java.io.IOException;

/**
 * Created by whartsell on 1/20/2015.
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    private Aircraft aircraft;

    public MainApp() {
        aircraft = new Aircraft("A-10C");

    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TOLD Calc");

        initRootLayout();
        showAircraftOverview();

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
        AircraftOverviewControl aircraftOverview = new AircraftOverviewControl();
        rootLayout.setCenter(aircraftOverview);

//            AircraftOverviewControl controller = loader.getController();
        aircraftOverview.setAircraft(aircraft);
    }


    public Aircraft getAircraft() {
        return aircraft;
    }
}
