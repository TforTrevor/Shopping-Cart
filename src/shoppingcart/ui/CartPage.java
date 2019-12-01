package shoppingcart.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import shoppingcart.User;

import java.io.FileNotFoundException;
import java.io.IOException;

public class CartPage extends BorderPane {
    GridPane grid = new GridPane();
    User userManager = User.getInstance();

    public CartPage() {

        Text title = new Text("Your Shopping Cart");
        Label username = new Label("Username:");
        Label password = new Label("Password:");
        Label error = new Label();
        TextField usernameField = new TextField();
        TextField vendorName = new TextField();
        PasswordField passwordField = new PasswordField();
        Button logIn = new Button("Log In");
        Button makeAcc = new Button("New Account");
        Button confirm = new Button("Confirm new account");
        CheckBox vendor = new CheckBox("Vendor?");

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(title, 0, 0);
        grid.add(username, 0, 1);
        grid.add(usernameField, 1, 1);
        grid.add(password, 0, 2);
        grid.add(passwordField, 1, 2);
        grid.add(logIn, 0, 3);
        grid.add(makeAcc, 1, 3);
        grid.add(error, 0, 4, 2, 1);
    }
}