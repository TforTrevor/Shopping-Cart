package shoppingcart.ui;

import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import shoppingcart.User;

public class UserProfile extends BorderPane {

    public UserProfile(User user) {
        TextField oldPassword = new TextField();
        TextField newPassword = new TextField();
        TextField confirmPassword = new TextField();
    }
}
