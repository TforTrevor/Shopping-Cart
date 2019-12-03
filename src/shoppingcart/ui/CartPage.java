package shoppingcart.ui;

import javafx.beans.property.ObjectProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import shoppingcart.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class CartPage extends BorderPane {
    GridPane grid = new GridPane();

    public CartPage() throws FileNotFoundException, CloneNotSupportedException {

        BorderPane borderPane = new BorderPane(); //general pane

        Text title = new Text("Your Shopping Cart"); //title of page
        int counter = CartManager.getCounter(); //the counter at the top
        Label counterView = new Label(counter + " items"); //displays how many items are in the cart

        ArrayList<Item> buffer = CartManager.getCart(); //retrieving the full cart list from the model

        FlowPane flowPane = new FlowPane(Orientation.VERTICAL);
        flowPane.setHgap(10);
        flowPane.setVgap(10);
        for (Item item: buffer) { //for every item in the list
            borderPane.setTop(new ItemNode(item));
            Label quantity = new Label("Quantity: " + item.getCartQuantity());
            Label price = new Label("Total Price: " + item.getPrice() * item.getCartQuantity());
            BorderPane labels = new BorderPane();

            labels.setTop(quantity);//contain quantity and price
            labels.setBottom(price);
            borderPane.setBottom(labels); // hold the item above the labels
            BorderPane.setAlignment(quantity, Pos.CENTER);

            flowPane.getChildren().addAll(new ItemNode(item), labels);
        }
        flowPane.setColumnHalignment(HPos.CENTER); // align labels on left
        flowPane.setPrefWrapLength(200); // preferred height = 200
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));
        this.setCenter(scrollPane);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(title, 0, 0);
        grid.add(counterView, 1, 0);

        Button checkoutButton = new Button("Checkout Now"); //button to trigger checkout page
        checkoutButton.setOnAction((event) -> {
            try {
                PageManager.getInstance().setPage(new CheckoutPage());
            } catch (FileNotFoundException | CloneNotSupportedException e) {
                e.printStackTrace();
            }

        });
        checkoutButton.setPadding(new Insets(10,5,10,5));
        BorderPane header = new BorderPane();
        header.setCenter(grid);
        header.setRight(checkoutButton);

        this.setTop(header); //display header on top
    }
}