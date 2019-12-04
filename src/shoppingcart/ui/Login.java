package shoppingcart.ui;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import shoppingcart.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Login extends BorderPane {

    private GridPane grid = new GridPane();

    public Login() {
        Text title = new Text("Shopping Cart");
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
        this.setCenter(grid);
        logIn.setDefaultButton(true);
        logIn.setOnAction(event -> {
            error.setText("");
            if (usernameField.getText().length() != 0) {
                if (passwordField.getText().length() != 0) {
                    try {
                        User user = UserManager.checkUser(usernameField.getText(), passwordField.getText());
                        if (user != null) {
                            Cart cart = new Cart(); //when a person logs in, instantiate the cart to their account
                            UserManager.setLoggedInUser(user);
                            CartManager.setYourCart(cart);//set their cart to the instance
                            PageManager.getInstance().setPage(new StorePage());
                            PageManager.getInstance().setHeader(new Header());
                        } else {
                            error.setText("Error! Incorrect username/password.");
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println("File location not found.");
                        error.setText("Error processing log in, please try again");
                    } catch (IOException | CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                } else error.setText("Error! Please enter your password.");
            } else error.setText("Error! Please enter your username.");
        });
        makeAcc.setOnAction(event -> {
            error.setText("");
            logIn.setDefaultButton(false);
            confirm.setDefaultButton(true);
            grid.getChildren().remove(logIn);
            grid.getChildren().remove(makeAcc);
            grid.getChildren().remove(error);
            grid.add(vendor, 0, 3);
            grid.add(vendorName, 1, 3);
            grid.add(error, 0, 5);
            grid.add(confirm, 1, 4);
            vendorName.setDisable(true);

        });
        vendor.setOnAction(event -> {
            if (!(vendor.isSelected())) {
                vendorName.setDisable(true);
            } else {
                vendorName.setDisable(false);
            }
        });
        confirm.setOnAction(event -> {
            if (usernameField.getText().length() != 0) {
                if (passwordField.getText().length() != 0) {
                    try {
                        String vendorProcess = (vendor.isSelected()) ? vendorName.getText() : null;
                        User user = UserManager.makeNewUser(usernameField.getText(), passwordField.getText(), vendorProcess, (vendor.isSelected()));
                        Cart cart = new Cart();//same thing
                        if (user != null) {
                            UserManager.setLoggedInUser(user);
                            if(user.checkVendor()) {
                                ItemManager.createStore(user.getVendor());
                            }
                            CartManager.setYourCart(cart);
                            PageManager.getInstance().setPage(new StorePage());
                            PageManager.getInstance().setHeader(new Header());
                        } else {
                            error.setText("Error! Username/vendor name is already taken.");
                        }
                    } catch (IOException | CloneNotSupportedException e) {
                        System.out.println("Error inserting user to file.");
                        error.setText("Error making a new account, please try again");
                    }
                } else error.setText("Error! Please enter a password.");
            } else error.setText("Error! Please enter a username.");
        });
    }
}
