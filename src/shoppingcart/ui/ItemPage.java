package shoppingcart.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import shoppingcart.Item;
import shoppingcart.Utilities;

public class ItemPage extends VBox {

    private Item item;

    private Label title;
    private BorderPane content;

    public ItemPage(Item item) {
        this.item = item;

        title = new Label(item.getName());
        this.getChildren().add(title);
        content = new BorderPane();
        VBox demoImages = new VBox();
        for (int i = 0; i < 5; i++) {
            demoImages.getChildren().add(new Button("Demo Image " + i));
        }
        Button mainImage = item.getImage();
        Utilities.makeNodeFill(mainImage);
        Button purchase = new Button("Purchase");
        Utilities.makeNodeFill(purchase);
        VBox description = new VBox(new Label("Description"), new Label("Longer description"));
        BorderPane centerContent = new BorderPane();
        centerContent.setCenter(new AnchorPane(mainImage));
        centerContent.setBottom(description);
        content.setLeft(demoImages);
        content.setCenter(centerContent);
        content.setRight(new AnchorPane(purchase));
        this.getChildren().add(content);
    }
}
