package net.willshouse.planner.testui.view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;

/**
 * Created by whartsell on 1/21/15.
 */
public class RootLayout extends BorderPane {
    MenuBar menuBar;


    public RootLayout() {
        menuBar = new MenuBar();
        menuBar.getMenus().add(new Menu("Test"));

        this.setTop(menuBar);
        this.setPrefSize(1280, 720);
    }

}
