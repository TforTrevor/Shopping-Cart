package shoppingcart.ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import shoppingcart.User;

public class PageManager {

    private static PageManager instance;
    private Stage stage;
    private Scene scene;

    private BorderPane content = new BorderPane();
    private Header header;

    private User isInitialized = User.getInstance();

    private PageManager() {

    }

    public static PageManager getInstance() {
        if (instance == null) {
            instance = new PageManager();
        }
        return instance;
    }

    public void initialize(Stage stage) {
        this.stage = stage;
        scene = new Scene(content, 1280, 720);
        //content.setTop(header);
        stage.setTitle("Shopping Cart");
        stage.setScene(scene);
        stage.show();
    }

    public void setPage(Parent parent) {
        if(isInitialized.isInitialized()) {
            if(header == null)
                header = new Header();
            content.setTop(header);
        }
        content.setCenter(parent);
    }
}
