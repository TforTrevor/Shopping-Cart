package shoppingcart.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import shoppingcart.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StorePage extends BorderPane {

    private StoreManager itemSetup = new StoreManager();


    public StorePage() throws IOException, CloneNotSupportedException {

        Text title = new Text("Shopping Cart");
        VBox titles = new VBox();
        titles.getChildren().add(title);
        titles.setSpacing(0);
        titles.setAlignment(Pos.CENTER);
        BorderPane content = new BorderPane();
        content.setCenter(titles);
        title.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 32));
        title.setFill(Color.WHITE);
        content.setPadding(new Insets(10,0,30, 0));
        content.setBackground(new Background(new BackgroundFill(Color.valueOf("333"), CornerRadii.EMPTY, Insets.EMPTY)));

        ArrayList<Item> buffer = itemSetup.getItems();
        ArrayList<ItemNode> itemNodes = new ArrayList<>();


        for (Item item : buffer) {
            itemSetup.setAvailableQuantities(item, item.getAvailableQuantity());
            itemSetup.setCartQuantities(item, item.getCartQuantity());
            itemNodes.add(new ItemNode(item));
        }
        VBox mainContent = new VBox(content);
        mainContent.setSpacing(10);
        mainContent.getChildren().add(new Carousel<>("Store Items", itemNodes));

        this.setCenter(mainContent);
    }
}
