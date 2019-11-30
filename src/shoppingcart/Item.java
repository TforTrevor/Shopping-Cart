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

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Item extends BorderPane {

    private Image image;
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

    public String getName() throws CloneNotSupportedException {
        return getItem().getName();
    }

    public Image getImage() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream(item.getPhoto());
        image = new Image(inputStream);
        return image;
    }

    public ItemType getItem() throws CloneNotSupportedException { return (ItemType) item.clone(); }
}
