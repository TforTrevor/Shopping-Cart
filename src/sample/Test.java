package sample;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Test extends AnchorPane {

    Button test = new Button("Test");

    public Test() {
        this.setPadding(new Insets(5));
        AnchorPane.setLeftAnchor(test, 0.0);
        AnchorPane.setRightAnchor(test, 0.0);
        AnchorPane.setTopAnchor(test, 0.0);
        AnchorPane.setBottomAnchor(test, 0.0);
        this.getChildren().add(test);
    }
}
