package shoppingcart;

import javafx.application.Application;
import javafx.stage.Stage;
import shoppingcart.ui.PageManager;
import shoppingcart.ui.StorePage;
import shoppingcart.ui.UserPage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        PageManager.getInstance().initialize(primaryStage);
        PageManager.getInstance().setPage(new UserPage());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
