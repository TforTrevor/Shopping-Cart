package shoppingcart.ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

public class PageManager {

    private static PageManager instance;
    private Stage stage;
    private Scene scene;

    private BorderPane content = new BorderPane();

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

        ScenicView.show(scene);
    }

    public void setHeader(Parent parent) {
        content.setTop(parent);
    }

    public void setPage(Parent parent) {
        content.setCenter(parent);
    }
}
