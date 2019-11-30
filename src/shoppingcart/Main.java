package shoppingcart;

import javafx.application.Application;
import javafx.stage.Stage;
import shoppingcart.ui.Login;
import shoppingcart.ui.PageManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        PageManager.getInstance().initialize(primaryStage);
        PageManager.getInstance().setPage(new Login());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
