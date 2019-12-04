package shoppingcart.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import shoppingcart.CartManager;
import shoppingcart.User;
import shoppingcart.UserManager;
import shoppingcart.Utilities;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Header extends BorderPane {

    private AnchorPane searchBarPane;
    private TextField searchBar;
    private HBox options;
    private Button vendorButton;
    private Button homeButton;
    private static Button cartButton;
    private HBox buttonHolder;

    public Header() {
        searchBar = new TextField();
        Utilities.makeNodeFill(searchBar);
        searchBarPane = new AnchorPane(searchBar);
        searchBarPane.setPadding(new Insets(0, 5, 0, 5));

        options = new HBox(new Label("Username"), new Button("Options"));
        options.setSpacing(5);

        buttonHolder = new HBox();
        buttonHolder.setSpacing(5);

        cartButton = new Button("Your Cart: " + CartManager.getCounter());
        cartButton.setOnAction((event -> {
            try {
                PageManager.getInstance().setPage(new CartPage());
            } catch (CloneNotSupportedException | IOException e) {
                e.printStackTrace();
            }
        }));

        vendorButton = new Button("For Vendors");
        vendorButton.setOnAction((event -> {
            try {
                PageManager.getInstance().setPage(new VendorPage());
            } catch (IOException | CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }));

        homeButton = new Button("Home");
        homeButton.setOnAction((event) -> {
            try {
                PageManager.getInstance().setPage(new StorePage());
            } catch (IOException | CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        Button profileButton = new Button("Profile");
        profileButton.setOnAction((event) -> {
            PageManager.getInstance().setPage(new UserProfile(UserManager.getLoggedInUser()));
        });
        buttonHolder.getChildren().add(profileButton);
        User user = UserManager.getLoggedInUser();
        this.setLeft(homeButton);
        this.setCenter(searchBarPane);
        if(user.getVendor() != null)
            buttonHolder.getChildren().add(vendorButton);
        buttonHolder.getChildren().add(cartButton);
        this.setRight(buttonHolder);
        this.setPadding(new Insets(15));
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public static void updateCartButton(){
        cartButton.setText("Your Cart: " + CartManager.getCounter());
    }
}
