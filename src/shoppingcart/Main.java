package shoppingcart;

import javafx.application.Application;
import javafx.stage.Stage;
import shoppingcart.ui.Login;
import shoppingcart.ui.PageManager;

/**
 * @author Aaron Zygala, Leo Herrera
 * Class to control the initial page of the application
 */
public class Main extends Application {

    /**
     * Function to properly initialize the application,
     * sets the first screen to the LogIn screen
     * @param primaryStage The first stage of the application
     *
     */
    @Override
    public void start(Stage primaryStage) {

        PageManager.getInstance().initialize(primaryStage);
        PageManager.getInstance().setPage(new Login());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
