package shoppingcart.ui;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import shoppingcart.Utilities;

public class Modal extends BorderPane {

    public Modal(String header, Node content) {
        Label headerLabel = new Label(header);
        headerLabel.setFont(Font.font(32));
        Button exit = new Button("X");
        exit.setOnAction((event) -> {
            close();
        });
        Utilities.makeNodeFill(exit);
        AnchorPane exitContainer = new AnchorPane(exit);
        exitContainer.setPadding(new Insets(5));
        BorderPane topContainer = new BorderPane();
        topContainer.setCenter(headerLabel);
        topContainer.setRight(exitContainer);

        Utilities.makeNodeFill(content);
        AnchorPane center = new AnchorPane(content);
        center.setPadding(new Insets(24));
        center.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        this.setCenter(center);
        this.setTop(topContainer);
        this.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    public void show() {
        PageManager.getInstance().showPopUp(this);
    }

    public void show(double top, double bottom, double left, double right) {
        PageManager.getInstance().showPopUp(this, top, bottom, left, right);
    }

    public void close() {
        PageManager.getInstance().closePopUp(this);
    }
}
