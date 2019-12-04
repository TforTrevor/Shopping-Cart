package shoppingcart.ui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

public class Carousel<T extends Node> extends BorderPane {

    public Carousel(String header, ArrayList<T> items) {
        HBox content = new HBox();
        content.setSpacing(10);
        for (T item : items) {
            content.getChildren().add(item);
        }
        ScrollPane scrollPane = new ScrollPane(content);
        Label headerLabel = new Label(header);
        headerLabel.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 32));

        this.setTop(headerLabel);
        this.setCenter(scrollPane);

        this.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setPadding(new Insets(20));

       // Platform.runLater(() -> {
        //    scrollPane.setPrefViewportHeight(items.get(0).getHeight());
        //});
    }
}
