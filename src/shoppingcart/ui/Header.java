package shoppingcart.ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import shoppingcart.Utilities;

public class Header extends BorderPane {

    private AnchorPane searchBarPane;
    private TextField searchBar;
    private HBox options;
    private Button homeButton;

    public Header() {
        searchBar = new TextField();
        Utilities.makeNodeFill(searchBar);
        searchBarPane = new AnchorPane(searchBar);
        searchBarPane.setPadding(new Insets(0, 5, 0, 0));

        options = new HBox(new Label("Username"), new Button("Options"));
        options.setSpacing(5);
        homeButton = new Button("Home");
        homeButton.setOnAction((event) -> {
            PageManager.getInstance().setPage(new StorePage());
        });
        this.setLeft(homeButton);
        this.setCenter(searchBarPane);
        this.setRight(options);
        this.setPadding(new Insets(5));
    }
}
