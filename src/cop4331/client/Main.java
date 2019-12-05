package cop4331.client;

import cop4331.gui.Login;
import cop4331.gui.PageManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

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

        new File("data/Cart").mkdirs();
        new File("data/Photos").mkdirs();
        new File("data/Receipts").mkdirs();
        new File("data/Vendors").mkdirs();

        PageManager.getInstance().initialize(primaryStage);
        PageManager.getInstance().setPage(new Login());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
