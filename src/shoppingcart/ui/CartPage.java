package shoppingcart.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import shoppingcart.Cart;
import shoppingcart.CartManager;
import shoppingcart.UserManager;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CartPage extends BorderPane {
    GridPane grid = new GridPane();
    public CartPage() {

        Text title = new Text("Your Shopping Cart");
        Integer counter = CartManager.getCounter();
        Label counterView = new Label(counter.toString());
        Label error = new Label();


        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(title, 0, 0);
        grid.add(counterView, 1, 0);
        grid.add(error, 0, 4, 2, 1);
        this.setCenter(grid);
    }
}