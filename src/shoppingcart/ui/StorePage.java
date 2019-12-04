package shoppingcart.ui;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import shoppingcart.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StorePage extends BorderPane {

    private ScrollPane scrollPane = new ScrollPane();
    private FlowPane flowPane = new FlowPane();
    private StoreManager itemSetup = new StoreManager();


    public StorePage() throws IOException, CloneNotSupportedException {
        ArrayList<Item> buffer = itemSetup.getItems();

        for (Item item : buffer) {
            //item.setPrefSize(100, 100);
            itemSetup.setAvailableQuantities(item, item.getAvailableQuantity());
            itemSetup.setCartQuantities(item, item.getCartQuantity());
            flowPane.getChildren().add(new ItemNode(item));
        }
        flowPane.setHgap(5);
        flowPane.setVgap(5);
        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));
        this.setCenter(scrollPane);
    }
}
