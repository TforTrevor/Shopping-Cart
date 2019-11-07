package shoppingcart;

import javafx.application.Application;
import javafx.stage.Stage;
import shoppingcart.ui.PageManager;
import shoppingcart.ui.StorePage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        PageManager.getInstance().initialize(primaryStage);
        PageManager.getInstance().setPage(new StorePage());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
