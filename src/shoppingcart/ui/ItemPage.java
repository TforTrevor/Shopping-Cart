package shoppingcart.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import shoppingcart.CartManager;
import shoppingcart.Item;
import shoppingcart.Utilities;

import javax.swing.*;

public class ItemPage extends BorderPane {

    private BorderPane content;


    public ItemPage(Item item) {

        Label title = new Label(item.getName());
        title.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 32));
        title.setTextFill(Color.WHITE);
        HBox topContainer = new HBox(title);
        topContainer.setPadding(new Insets(10, 20, 10, 20));
        topContainer.setBackground(new Background(new BackgroundFill(Color.valueOf("333"), CornerRadii.EMPTY, Insets.EMPTY)));

        ImageView mainImage = new ImageView(item.getImage(500, 500));

        Label details = new Label("Details:");
        details.setFont(Font.font(20));
        Label description = new Label(item.getDescription());
        description.setWrapText(true);
        VBox bottomContainer = new VBox(details, description);
        bottomContainer.setPadding(new Insets(20));

        Label price = new Label("$" + item.getPrice());
        price.setFont(Font.font(20));
        Label quantity = new Label("Stock: " + item.getAvailableQuantity());
        quantity.setFont(Font.font(16));
        Label vendor = new Label("Vendor: " + item.getVendorName());
        vendor.setFont(Font.font(16));
        Button addToCart = new Button("Add to Cart");
        addToCart.setFont(Font.font(16));
        Spinner spinner = new Spinner(1, item.getAvailableQuantity(), 1);
        addToCart.setOnAction(event -> {
            addToCart(item, (Integer)spinner.getValue());
        });

        Utilities.makeNodeFill(addToCart);
        AnchorPane addToCartContainer = new AnchorPane(addToCart);
        AnchorPane spinnerContainer = new AnchorPane(spinner);
        addToCartContainer.setPadding(new Insets(20, 10, 20, 10));
        addToCartContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        VBox temp = new VBox(price, quantity, vendor);
        temp.setSpacing(5);
        temp.setPadding(new Insets(10));
        temp.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        BorderPane rightContainer = new BorderPane();
        BorderPane itemPurchaser = new BorderPane();
        itemPurchaser.setLeft(addToCartContainer);
        itemPurchaser.setRight(spinnerContainer);
        rightContainer.setCenter(temp);
        rightContainer.setBottom(itemPurchaser);
        rightContainer.setPadding(new Insets(20));

        StackPane gap = new StackPane();
        gap.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        gap.setMinHeight(10);

        this.setTop(topContainer);
        this.setCenter(mainImage);
        this.setBottom(bottomContainer);
        this.setRight(rightContainer);
    }

    private void addToCart(Item item, int q) {
        CartManager.changeQuantity(item.getAvailableQuantity() - q,item);
        CartManager.addItem(item);
    }
}
