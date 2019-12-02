package shoppingcart.ui;

import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
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
    private ScrollPane scrollPane = new ScrollPane();
    private FlowPane flowPane = new FlowPane();
    private BorderPane borderPane = new BorderPane();
    public CartPage() throws FileNotFoundException, CloneNotSupportedException {

        Text title = new Text("Your Shopping Cart");
        Integer counter = CartManager.getCounter();
        Label counterView = new Label(counter.toString() + " items");

        ArrayList<Item> buffer = CartManager.getCart();
        int i = 0;
        for (Item item : buffer) {
            //if(item.getCartQuantity() > 1 && i > 0)
              //  continue;
            borderPane.setTop(new ItemNode(item));
            Label quantity = new Label("Quantity: " + item.getCartQuantity());
            Label price = new Label("Total Price: " + item.getPrice() * item.getCartQuantity());
            BorderPane labels = new BorderPane();
            labels.setTop(quantity);
            labels.setBottom(price);
            borderPane.setBottom(labels);
            BorderPane.setAlignment(quantity, Pos.CENTER);
            flowPane.getChildren().add(borderPane);
            i++;
        }
        flowPane.setHgap(5);
        flowPane.setVgap(5);
        scrollPane.setContent(flowPane);
        scrollPane.setFitToWidth(true);
        scrollPane.setPadding(new Insets(10));
        this.setCenter(scrollPane);

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(title, 0, 0);
        grid.add(counterView, 1, 0);

        Button checkoutButton = new Button("Checkout Now");
        checkoutButton.setOnAction((event) -> {
            try {
                PageManager.getInstance().setPage(new CheckoutPage());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

        });
            checkoutButton.setPadding(new Insets(10,5,10,5));
        BorderPane header = new BorderPane();
        header.setCenter(grid);
        header.setRight(checkoutButton);

        this.setTop(header);
    }
}