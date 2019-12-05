package cop4331.client;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class Utilities {

    public static void makeNodeFill(Node node) {
        AnchorPane.setRightAnchor(node, 0.0);
        AnchorPane.setLeftAnchor(node, 0.0);
        AnchorPane.setTopAnchor(node, 0.0);
        AnchorPane.setBottomAnchor(node, 0.0);
    }
}
