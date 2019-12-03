package shoppingcart.ui;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;
import org.scenicview.ScenicView;

import java.util.ArrayList;
//import org.scenicview.ScenicView;

public class PageManager {

    private static PageManager instance;
    private Stage stage;
    private Scene scene;

    private StackPane stackPane = new StackPane();
    private BorderPane content = new BorderPane();
    private ArrayList<Pair<AnchorPane, Modal>> modals = new ArrayList<>();

    private PageManager() {

    }

    public Stage getStage() {
        return stage;
    }

    public static PageManager getInstance() {
        if (instance == null) {
            instance = new PageManager();
        }
        return instance;
    }

    public void initialize(Stage stage) {
        this.stage = stage;
        stackPane.getChildren().add(content);
        scene = new Scene(stackPane, 1280, 720);
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

    public void showPopUp(Modal modal) {
        showPopUp(modal, 0, 0, 0, 0);
    }

    public void showPopUp(Modal modal, double top, double bottom, double left, double right) {
        AnchorPane.setTopAnchor(modal, top);
        AnchorPane.setBottomAnchor(modal, bottom);
        AnchorPane.setLeftAnchor(modal, left);
        AnchorPane.setRightAnchor(modal, right);

        AnchorPane anchorPane = new AnchorPane(modal);
        anchorPane.setBackground(new Background(new BackgroundFill(Color.color(0, 0, 0, 0.5f), CornerRadii.EMPTY, Insets.EMPTY)));

        modals.add(new Pair<>(anchorPane, modal));
        stackPane.getChildren().add(anchorPane);
    }

    public void closePopUp(Modal modal) {
        for (Pair<AnchorPane, Modal> modalPair : modals) {
            if (modalPair.getValue() == modal) {
                modals.remove(modalPair);
                stackPane.getChildren().remove(modalPair.getKey());
                break;
            }
        }
    }
}
