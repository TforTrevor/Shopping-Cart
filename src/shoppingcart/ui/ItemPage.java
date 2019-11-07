package shoppingcart.ui;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import shoppingcart.Utilities;

public class ItemPage extends BorderPane {

    private Button backButton;

    public ItemPage(Item item) {
        this.setCenter(item);
        Utilities.makeNodeFill(item);
        backButton = new Button("Back");
        backButton.setOnAction((event) -> {
            PageManager.getInstance().setPage(new StorePage());
        });
        this.setTop(backButton);
    }
}
