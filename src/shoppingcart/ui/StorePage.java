package shoppingcart.ui;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import shoppingcart.Item;

public class StorePage extends BorderPane {

    ScrollPane scrollPane = new ScrollPane();
    FlowPane flowPane = new FlowPane();

    public StorePage() {
        for (int i = 0; i < 100; i++) {
            Item item = new Item("Item " + i);
            item.setPrefSize(100, 100);
            flowPane.getChildren().add(item);
        }
        flowPane.setHgap(5);
        flowPane.setVgap(5);
        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);
        this.setCenter(scrollPane);
    }
}
