package shoppingcart.ui;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import shoppingcart.Item;
import shoppingcart.ItemType;
import shoppingcart.Store;

import java.io.IOException;
import java.util.ArrayList;

public class StorePage extends BorderPane {

    private ScrollPane scrollPane = new ScrollPane();
    private FlowPane flowPane = new FlowPane();
    private Store itemSetup = new Store();
    public StorePage() throws IOException, CloneNotSupportedException {
        ArrayList<Item> buffer = itemSetup.getItems();
        for (Item item: buffer) {
            item.setPrefSize(100, 100);
            flowPane.getChildren().add(new ItemNode(item));
        }
        flowPane.setHgap(5);
        flowPane.setVgap(5);
        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);
        this.setCenter(scrollPane);
    }
}
