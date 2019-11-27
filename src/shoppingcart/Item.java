package shoppingcart;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import shoppingcart.Utilities;
import shoppingcart.ui.ItemPage;
import shoppingcart.ui.PageManager;

public class Item extends BorderPane {

    private Image image;
    private Button tempImage;
    private Label name;
    private ItemType item;

    public Item(ItemType item) {
        this.item = item;
//        name = new Label(string);
//        tempImage = new Button("Image");
//        tempImage.setOnAction((event) -> {
//            PageManager.getInstance().setPage(new ItemPage(this));
//        });
//        Utilities.makeNodeFill(tempImage);
//        this.setCenter(new AnchorPane(tempImage));
//        this.setBottom(name);
//        BorderPane.setAlignment(name, Pos.CENTER);
    }

    public String getName() {
        return name.getText();
    }

    public Button getImage() {
        return tempImage;
    }

    public ItemType getItem() {
        return item;
    }
}
