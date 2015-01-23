package net.willshouse.planner.ui.components;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.willshouse.planner.ui.components.model.Aircraft;

/**
 * Created by whartsell on 1/22/2015.
 */
public class testApp extends Application {
    private Stage primaryStage;
    private AircraftOverviewControl rootLayout;
    private Aircraft aircraft;

    public testApp() {
        aircraft = new Aircraft("A-10C");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TOLD Calc");
        rootLayout = new AircraftOverviewControl();
        rootLayout.setAircraft(aircraft);
        Scene scene = new Scene(rootLayout);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
