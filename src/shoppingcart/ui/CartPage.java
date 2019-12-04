package shoppingcart.ui;

import javafx.beans.property.ObjectProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import shoppingcart.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CartPage extends BorderPane {

    public CartPage() throws IOException, CloneNotSupportedException {

        ArrayList<Item> buffer = CartManager.getCart(); //retrieving the full cart list from the model

        Text title = new Text("Your Shopping Cart: ");
        int counter = CartManager.getCounter(); //the counter at the top
        Label counterView = new Label(counter + " items"); //displays how many items are in the cart

        HBox titles = new HBox(title, counterView);
        titles.setSpacing(5);
        titles.setAlignment(Pos.CENTER);
        BorderPane content = new BorderPane();
        content.setCenter(titles);
        title.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 32));
        title.setFill(Color.WHITE);
        counterView.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 32));
        counterView.setTextFill(Color.WHITE);
        content.setPadding(new Insets(10,0,30, 0));

        Button checkoutButton = new Button("Checkout Now"); //button to trigger checkout page

        if (buffer.isEmpty()) checkoutButton.setDisable(true);
        else checkoutButton.setDisable(false);

        checkoutButton.setOnAction((event) -> {
            try {
                PageManager.getInstance().setPage(new CheckoutPage());
            } catch (FileNotFoundException | CloneNotSupportedException e) {
                e.printStackTrace();
            }

        });
        GridPane grid = new GridPane();
        grid.add(content, 5, 0);
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(75);
        grid.add(checkoutButton, 8, 0);
        grid.setBackground(new Background(new BackgroundFill(Color.valueOf("333"), CornerRadii.EMPTY, Insets.EMPTY)));

        checkoutButton.setPadding(new Insets(10, 5, 10, 5));
        this.setTop(grid);


        BorderPane borderPane = new BorderPane();
        FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
        flowPane.setHgap(15);
        flowPane.setVgap(5);
        for (Item item: buffer) { //for every item in the list
            borderPane.setTop(new ItemNode(item));

            BorderPane quantityPane = new BorderPane();
            Label quantity = new Label("Quantity: " + item.getQuantity());
            Spinner<Integer> changeQuantity = new Spinner<>(0, item.getAvailableQuantity(), item.getQuantity());
            changeQuantity.getEditor().textProperty().addListener((obs, oldValue, newValue) ->{
                quantity.setText("Quantity: " + changeQuantity.getValue());
                int newQuantity = 0;
                try {
                    if(changeQuantity.getValue() >= item.getQuantity()){
                        try {
                            CartManager.addToCart(item, 1); //add to the cart
                            newQuantity = item.getAvailableQuantity() - 1;
                            System.out.println(newQuantity);
                        } catch (IOException | CloneNotSupportedException e) {
                            e.printStackTrace();
                        }

                    }
                    else{
                        CartManager.removeFromCart(item, 1);
                        newQuantity = item.getAvailableQuantity() + 1;
                    }
                    try {
                        StoreManager.saveAvailableQuantity(item, item.getAvailableQuantity());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    PageManager.getInstance().setPage(new CartPage());
                } catch (CloneNotSupportedException | IOException e) {
                    e.printStackTrace();
                }

                Header.updateCartButton();
            });

            changeQuantity.setMaxWidth(15);
            quantityPane.setLeft(quantity);
            quantityPane.setRight(changeQuantity);

            Label price = new Label("Total Price: " + item.getPrice() * item.getQuantity());
            Label stock = new Label("Stock: " + item.getAvailableQuantity());
            BorderPane labels = new BorderPane();
            BorderPane labels2 = new BorderPane();
            labels2.setTop(quantityPane);
            labels2.setBottom(price);
            labels.setTop(labels2);//contain quantity and price
            labels.setBottom(stock);
            borderPane.setBottom(labels); // hold the item above the labels
            BorderPane.setAlignment(labels, Pos.CENTER);

            try {
                    flowPane.getChildren().addAll(new ItemNode(item), labels);
                } catch (CloneNotSupportedException | FileNotFoundException e) {
                    e.printStackTrace();
                }
        }
        flowPane.setColumnHalignment(HPos.CENTER); // align labels on left
        flowPane.setPrefWrapLength(200); // preferred height = 200
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));
        this.setCenter(scrollPane);

    }
}