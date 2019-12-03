package shoppingcart.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class CreateItem extends BorderPane {

    private TextField name = new TextField();
    private TextField description = new TextField();
    private TextField price = new TextField();
    private TextField quantity = new TextField();
    private TextField availableQuantity = new TextField();
    private TextField cartQuantity = new TextField();
    private TextField vendorName = new TextField();
    private TextField photo = new TextField();

    public CreateItem(String vendorName) {
        this.vendorName.setText(vendorName);
        this.vendorName.setEditable(false);

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Name: "),0, 0);
        gridPane.add(name,1, 0);
        gridPane.add(new Label("Description: "),0, 1);
        gridPane.add(description,1, 1);
        gridPane.add(new Label("Price: "),0, 2);
        gridPane.add(price,1, 2);
        gridPane.add(new Label("Quantity"),0, 3);
        gridPane.add(quantity,1, 3);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5);

        Button submit = new Button("Submit");
        submit.setOnAction((event) -> {
            createItem();
        });

        //VBox temp = new VBox(name, description, price, quantity, availableQuantity, cartQuantity, this.vendorName, photo);
        this.setCenter(gridPane);
        this.setBottom(submit);
        this.setAlignment(submit, Pos.CENTER);
    }

    private void createItem() {
        //Write to JSON
    }
}
