package shoppingcart.ui;

import javafx.beans.property.ObjectProperty;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import shoppingcart.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class CartPage extends BorderPane {
    GridPane grid = new GridPane();

    public CartPage() throws FileNotFoundException, CloneNotSupportedException {

        BorderPane borderPane = new BorderPane(); //general pane

        Text title = new Text("Your Shopping Cart"); //title of page
        int counter = CartManager.getCounter(); //the counter at the top
        Label counterView = new Label(counter + " items"); //displays how many items are in the cart

        ArrayList<Item> buffer = CartManager.getCart();
        ArrayList<Node> nodes = new ArrayList<>();

        for (Item item : buffer) {
            BorderPane fullNode = new BorderPane();

            Label quantity = new Label("Quantity: " + item.getQuantity());
            Label price = new Label("Total Price: " + item.getPrice() * item.getQuantity());
            BorderPane labels = new BorderPane();
            labels.setTop(quantity);
            labels.setBottom(price);

            Spinner<Integer> removeAmount = new Spinner<>(1, item.getQuantity(), 1);
            Button removeItem = new Button("Remove " + removeAmount.getValue() + " From Cart");
            removeAmount.getEditor().textProperty().addListener((obs, oldValue, newValue) ->
                    removeItem.setText("Remove " + removeAmount.getValue() + " From Cart"));
            removeAmount.setMaxWidth(15);

            Spinner<Integer> addAmount = new Spinner<>(1, item.getAvailableQuantity(), 1);
            Button addItem = new Button("Add " + addAmount.getValue() + " To Your Cart  ");
            addAmount.getEditor().textProperty().addListener((obs, oldValue, newValue) ->
                    addItem.setText("Add " + addAmount.getValue() + " To Your Cart  "));
            addAmount.setMaxWidth(15);
            addItem.setMinWidth(50);
            removeItem.setMinWidth(50);

            removeItem.setAlignment(Pos.CENTER);
            BorderPane button1 = new BorderPane();
            BorderPane button2 = new BorderPane();
            BorderPane buttons = new BorderPane();
            BorderPane labelsButtons = new BorderPane();

            button1.setLeft(removeItem);
            button1.setRight(removeAmount);
            button2.setLeft(addItem);
            button2.setRight(addAmount);
            buttons.setTop(button1);
            buttons.setBottom(button2);
            removeItem.setOnAction(event -> {
                try {
                    CartManager.removeFromCart(item, removeAmount.getValue());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    PageManager.getInstance().setPage(new CartPage());
                } catch (IOException | CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                Header.updateCartButton();
            });
            addItem.setOnAction(event -> {
                try {
                    CartManager.addToCart(item, addAmount.getValue());
                } catch (IOException | CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                try {
                    PageManager.getInstance().setPage(new CartPage());
                } catch (IOException | CloneNotSupportedException e) {
                    e.printStackTrace();
                }
                Header.updateCartButton();
            });
            labelsButtons.setTop(labels);
            labelsButtons.setBottom(buttons);
            fullNode.setTop(new ItemNode(item));
            fullNode.setBottom(labelsButtons);

            nodes.add(fullNode);
        }


        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(title, 0, 0);
        grid.add(counterView, 1, 0);
        title.setFill(Color.WHITE);
        title.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 32));
        counterView.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 20));
        counterView.setTextFill(Color.WHITE);
        Button checkoutButton = new Button("Checkout Now"); //button to trigger checkout page

        if (buffer.isEmpty()) checkoutButton.setDisable(true);
        else checkoutButton.setDisable(false);

        checkoutButton.setOnAction((event) -> {
            try {
                PageManager.getInstance().setPage(new CheckoutPage());
            } catch (FileNotFoundException | CloneNotSupportedException e) {
                e.printStackTrace();
            }

        });

        checkoutButton.setPadding(new Insets(10,5,10,5));
        VBox titles = new VBox();
        titles.getChildren().add(title);
        titles.getChildren().add(counterView);
        titles.setSpacing(0);
        titles.setAlignment(Pos.CENTER);
        HBox header = new HBox(titles, checkoutButton);
        header.setAlignment(Pos.CENTER);

        header.setSpacing(20);
        BorderPane content = new BorderPane();
        content.setCenter(header);
        title.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 32));
        counterView.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 20));
        title.setFill(Color.WHITE);
        counterView.setTextFill(Color.WHITE);
        content.setPadding(new Insets(30,0,30, 0));
        content.setBackground(new Background(new BackgroundFill(Color.valueOf("333"), CornerRadii.EMPTY, Insets.EMPTY)));


        VBox itemCarousel = new VBox(grid);

        itemCarousel.setSpacing(10);
        itemCarousel.getChildren().add(new Carousel<>("Your Cart", nodes));
        this.setCenter(itemCarousel);
        this.setTop(content);
    }
}