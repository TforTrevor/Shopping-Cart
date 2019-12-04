package shoppingcart.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import shoppingcart.Item;
import shoppingcart.StoreManager;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import shoppingcart.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StorePage extends BorderPane {

    private StoreManager itemSetup = new StoreManager();


    public StorePage() throws IOException, CloneNotSupportedException {

        Text title = new Text("Shopping Cart");
        VBox titles = new VBox();
        titles.getChildren().add(title);
        titles.setSpacing(0);
        titles.setAlignment(Pos.CENTER);
        BorderPane content = new BorderPane();
        content.setCenter(titles);
        title.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 32));
        title.setFill(Color.WHITE);
        content.setPadding(new Insets(10,0,30, 0));
        content.setBackground(new Background(new BackgroundFill(Color.valueOf("333"), CornerRadii.EMPTY, Insets.EMPTY)));

        ArrayList<Item> buffer = itemSetup.getItems();
        ArrayList<String> previousVendors = new ArrayList<>();
        VBox itemCarousel = new VBox(content);

        for (Item item : buffer) {
                if(previousVendors.contains(item.getVendorName()))
                    continue;
                previousVendors.add(item.getVendorName());
                ArrayList<Node> nodes = new ArrayList<>();

                for(Item i : buffer){
                    BorderPane fullNode = new BorderPane();
                    Spinner<Integer> addAmount = new Spinner<>(1, item.getAvailableQuantity(), 1);
                    Button quickAdd = new Button("Add " + addAmount.getValue()  + " to Cart");
                    quickAdd.setAlignment(Pos.CENTER);

                    addAmount.getEditor().textProperty().addListener((obs, oldValue, newValue) ->
                            quickAdd.setText("Add " + addAmount.getValue() + " to Cart"));
                    addAmount.setMaxWidth(15);
                    BorderPane buttons = new BorderPane();
                    buttons.setLeft(quickAdd);
                    buttons.setRight(addAmount);
                    fullNode.setBottom(buttons);

                    if(i.getVendorName().equals(item.getVendorName())){
                        fullNode.setTop(new ItemNode(i));
                        nodes.add(fullNode);
                    }

                    quickAdd.setOnAction(event -> {
                        try {
                            CartManager.addToCart(item, addAmount.getValue());
                        } catch (IOException | CloneNotSupportedException e) {
                            e.printStackTrace();
                        }
                        try {
                            item.setAvailableQuantity(item.getAvailableQuantity() - addAmount.getValue());
                            StoreManager.saveAvailableQuantity(item, item.getAvailableQuantity());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Header.updateCartButton();

                    });

                }

                if(nodes.size() > 0){
                    itemCarousel.setSpacing(10);
                    itemCarousel.getChildren().add(new Carousel<>(item.getVendorName(), nodes));
                }
            }
            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setContent(itemCarousel);
            scrollPane.setFitToWidth(true);
            this.setCenter(scrollPane);
        }
    }

