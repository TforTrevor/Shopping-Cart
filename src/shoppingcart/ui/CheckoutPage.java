package shoppingcart.ui;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import shoppingcart.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CheckoutPage extends BorderPane {
    GridPane grid = new GridPane();
    private ScrollPane scrollPane = new ScrollPane();
    private FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
    private BorderPane borderPane = new BorderPane();

    public CheckoutPage() throws FileNotFoundException, CloneNotSupportedException {

        BorderPane cartView = new BorderPane();
        Text title = new Text("Checkout");
        title.setFont(Font.font(20));

        int counter = CartManager.getCounter();//same as cartpage
        Label counterView = new Label(counter + " items");
        Label totalPrice = new Label("Total Price of the Cart: $" + CartManager.totalPrices());


        ArrayList<Item> buffer = CartManager.getCart();
        for (Item item : buffer) {
            borderPane.setTop(new ItemNode(item));
            Label quantity = new Label("Quantity: " + item.getQuantity());
            Label price = new Label("Total Price: " + item.getPrice() * item.getQuantity());
            BorderPane labels = new BorderPane();
            labels.setTop(quantity);//contain quantity and price
            labels.setBottom(price);
            borderPane.setBottom(labels); // hold the item above the labels
            BorderPane.setAlignment(quantity, Pos.CENTER);

            flowPane.getChildren().addAll(new ItemNode(item), labels);
        }
        flowPane.setColumnHalignment(HPos.CENTER); // align labels on left
        flowPane.setPrefWrapLength(200); // preferred height = 200
        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(title, 0, 0);
        grid.add(counterView, 0, 1);
        grid.add(totalPrice, 0, 2);
        grid.setAlignment(Pos.CENTER);
        BorderPane header = new BorderPane();
        header.setCenter(grid);

        cartView.setTop(header);
        cartView.setCenter(scrollPane);
        this.setTop(cartView);

        GridPane payment = new GridPane();
        Label ad = new Label("Address:");
        payment.add(ad, 0, 1);

        TextField address = new TextField();
        payment.add(address, 1, 1);

        Label cc = new Label("Credit Card Number:");
        payment.add(cc, 0, 2);

        TextField creditCardNumber = new TextField();
        payment.add(creditCardNumber, 1, 2);
        Button paymentButton = new Button("Place your Order!");
        paymentButton.setOnAction(event -> {
            try {
                PageManager.getInstance().setPage(new StorePage());
                CartManager.checkout(); //checks out the whole cart, (empties the cart and sends you to the store again)
                Header.updateCartButton();//update the cart counter so that it says 0 items in cart again
            } catch (IOException | CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });


        payment.add(paymentButton, 1, 3);//general design
        payment.setPadding(new Insets(5));
        payment.setHgap(10);
        payment.setVgap(20);
        payment.setAlignment(Pos.CENTER);
        this.setCenter(payment);
    }
}