package shoppingcart.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import shoppingcart.*;

import java.io.IOException;

public class ItemPage extends BorderPane {

    private BorderPane content;
    private Label price;
    private Label quantity;
    private Spinner<Integer> spinner;

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

        price = new Label("$" + item.getPrice());
        price.setFont(Font.font(20));
        quantity = new Label("Available: " + item.getAvailableQuantity());
        quantity.setFont(Font.font(16));
        Label warning = new Label();

        Label vendor = new Label("Vendor: " + item.getVendorName());
        vendor.setFont(Font.font(16));
        Button addToCart = new Button("Add to Cart");
        addToCart.setFont(Font.font(16));
        if(item.getAvailableQuantity() > 0){
            spinner = new Spinner<>(1, item.getAvailableQuantity(), 1);
        }
        else{
            spinner = new Spinner<>(0, 0, 0);
            addToCart.setDisable(true);
            warning.setText("Out of stock!");
            warning.setFont(Font.font(16));
            warning.setTextFill(Color.RED);
        }
        spinner.getEditor().textProperty().addListener((obs, oldValue, newValue) ->
                price.setText("$" + (item.getPrice() * (spinner.getValue()))));

        if(item.getVendorName().equals(UserManager.getLoggedInUser().getVendor()))
            addToCart.setDisable(true);

        addToCart.setOnAction(event -> {

            try {
                CartManager.addToCart(item, spinner.getValue()); //add to the cart
            } catch (IOException | CloneNotSupportedException e) {
                e.printStackTrace();
            }
            item.setAvailableQuantity(item.getAvailableQuantity() - spinner.getValue()); //set the quantity to new quantity
            try {
                StoreManager store = new StoreManager();
                store.saveAvailableQuantity(item, item.getAvailableQuantity());//update the available quantity in json
            } catch (IOException e) {
                e.printStackTrace();
            }

            quantity.setText("Available: " + (item.getAvailableQuantity())); //update the quantity
            if(item.getAvailableQuantity() > 0){ //if you can still by more, set the spinners range
                spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, item.getAvailableQuantity()));
            }
            else{//if not, do not let them by more, range is 0 to 0
                spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,0));
            }

            Header.updateCartButton(); //whenever someone adds to cart, update the cart itself

        });

        Utilities.makeNodeFill(addToCart);
        AnchorPane addToCartContainer = new AnchorPane(addToCart);
        Label spinnerQuantity = new Label("How many would you like to add?");
        AnchorPane spinnerPane = new AnchorPane(spinner);
        BorderPane spinnerContainer = new BorderPane();
        spinnerContainer.setTop(spinnerQuantity);
        spinnerContainer.setBottom(spinnerPane);
        addToCartContainer.setPadding(new Insets(0, 10, 20, 10));
        addToCartContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        spinnerContainer.setPadding(new Insets(20, 10, 20, 10));
        spinnerContainer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        VBox infoBox = new VBox(price, quantity, vendor, warning);
        infoBox.setSpacing(5);
        infoBox.setPadding(new Insets(10));
        infoBox.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        BorderPane rightContainer = new BorderPane();
        BorderPane itemPurchaser = new BorderPane();
        itemPurchaser.setLeft(addToCartContainer);
        itemPurchaser.setRight(spinnerContainer);
        rightContainer.setCenter(infoBox);
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



}
