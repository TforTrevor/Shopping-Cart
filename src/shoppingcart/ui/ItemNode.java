package shoppingcart.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import shoppingcart.Item;
import shoppingcart.Utilities;

public class ItemNode extends BorderPane {
    private Item item;
    public ItemNode(Item item){
        this.item = item;
        name = new Label(string);
        tempImage = new Button("Image");
        tempImage.setOnAction((event) -> {
            PageManager.getInstance().setPage(new ItemPage(this));
        });
        Utilities.makeNodeFill(tempImage);
        this.setCenter(new AnchorPane(tempImage));
        this.setBottom(name);
        BorderPane.setAlignment(name, Pos.CENTER);
    }
}
