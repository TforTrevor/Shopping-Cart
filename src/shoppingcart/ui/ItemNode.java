package shoppingcart.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import shoppingcart.Item;
import shoppingcart.Utilities;

import java.io.FileNotFoundException;

public class ItemNode extends BorderPane {
    private Button wrapper;
    private Label name;
    private ImageView holder;

    public ItemNode(Item item) throws CloneNotSupportedException, FileNotFoundException {
        wrapper = new Button();
        name = new Label(item.getName());
        holder = new ImageView(item.getImage(100, 100));
        wrapper.setGraphic(holder);
        wrapper.setOnAction((event) -> {
            PageManager.getInstance().setPage(new ItemPage(item));
        });
        wrapper.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        Utilities.makeNodeFill(wrapper);
        this.setCenter(new AnchorPane(wrapper));
        this.setBottom(name);
        BorderPane.setAlignment(name, Pos.CENTER);
    }
}
