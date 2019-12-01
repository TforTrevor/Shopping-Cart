package shoppingcart.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import shoppingcart.UserManager;
import shoppingcart.Utilities;

import java.io.IOException;

public class Header extends BorderPane {

    private AnchorPane searchBarPane;
    private TextField searchBar;
    private HBox options;
    private Button vendorButton;
    private Button homeButton;
    private Button cartButton;
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

        cartButton = new Button("Your Cart");

        vendorButton = new Button("For Vendors");
        vendorButton.setOnAction((event -> {
            PageManager.getInstance().setPage(new VendorPage());
        }));

        buttonHolder.getChildren().add(cartButton);
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

        this.setLeft(homeButton);
        this.setCenter(searchBarPane);
        //if(user.isVendor())
        //    buttonHolder.getChildren().add(vendorButton);
        this.setRight(buttonHolder);
        this.setPadding(new Insets(15));
        this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
    }
}
