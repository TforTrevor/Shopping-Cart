package shoppingcart.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import shoppingcart.Item;
import shoppingcart.Utilities;

import java.io.FileNotFoundException;

public class ItemPage extends VBox {

    private Label title;
    private BorderPane content;

    public ItemPage(Item item) throws FileNotFoundException, CloneNotSupportedException {

        title = new Label(item.getName());
        this.getChildren().add(title);
        content = new BorderPane();
        VBox demoImages = new VBox();
        for (int i = 0; i < 5; i++) {
            demoImages.getChildren().add(new Button("Demo Image " + i));
        }
        ImageView mainImage = new ImageView(item.getImage());
        mainImage.setFitHeight(400);
        mainImage.setFitWidth(400);
        Utilities.makeNodeFill(mainImage);
        String urgent = (item.getItem().getAvailableQuantity() < 10)? "Hurry! less than " + item.getItem().getAvailableQuantity() + " left": "" ;
        Button purchase = new Button("Purchase \n" + urgent);
        purchase.setWrapText(true);
        Utilities.makeNodeFill(purchase);
        VBox description = new VBox(new Label(item.getItem().getDescription()), new Label("Price: $" + item.getItem().getPrice()));
        BorderPane centerContent = new BorderPane();
        centerContent.setCenter(mainImage);
        centerContent.setBottom(description);
        content.setLeft(demoImages);
        content.setCenter(centerContent);
        content.setRight(new AnchorPane(purchase));
        this.getChildren().add(content);
    }
}
