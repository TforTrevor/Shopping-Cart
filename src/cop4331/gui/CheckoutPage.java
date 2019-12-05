package cop4331.gui;

import cop4331.client.CartManager;
import cop4331.client.Item;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CheckoutPage extends BorderPane {
    GridPane grid = new GridPane();
    private ScrollPane scrollPane = new ScrollPane();
    private FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
    private BorderPane borderPane = new BorderPane();

    public CheckoutPage() throws FileNotFoundException, CloneNotSupportedException {

        int counter = CartManager.getCounter();//same as cartpage

        Text title = new Text("Checkout");
        Label counterView = new Label(counter + " items");
        Label totalPrice = new Label("Total Price of the Cart: $" + CartManager.totalPrices());
        VBox titles = new VBox(title, counterView, totalPrice);
        titles.setSpacing(5);
        titles.setAlignment(Pos.CENTER);
        BorderPane content = new BorderPane();
        content.setCenter(titles);
        title.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 32));
        title.setFill(Color.WHITE);
        counterView.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 20));
        counterView.setTextFill(Color.WHITE);
        totalPrice.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 20));
        totalPrice.setTextFill(Color.WHITE);
        content.setPadding(new Insets(10,0,30, 0));
        content.setBackground(new Background(new BackgroundFill(Color.valueOf("333"), CornerRadii.EMPTY, Insets.EMPTY)));

        BorderPane cartView = new BorderPane();


        flowPane.setHgap(8);
        flowPane.setVgap(8);
        ArrayList<Item> buffer = CartManager.getCart();
        for (Item item : buffer) {
            borderPane.setTop(new ItemNode(item));
            Label quantity = new Label("Quantity: " + item.getQuantity());
            Label price = new Label("Total Price: " + item.getPrice() * item.getQuantity());
            BorderPane labels = new BorderPane();
            labels.setTop(quantity);//contain quantity and price
            labels.setBottom(price);
            BorderPane.setAlignment(labels, Pos.CENTER);

            flowPane.getChildren().addAll(new ItemNode(item), labels);
        }

        flowPane.setColumnHalignment(HPos.CENTER); // align labels on left
        flowPane.setPrefWrapLength(200); // preferred height = 200
        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));

        cartView.setTop(content);
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