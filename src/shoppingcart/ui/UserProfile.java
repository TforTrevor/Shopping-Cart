package shoppingcart.ui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import shoppingcart.User;

public class UserProfile extends BorderPane {

    private Label errorMessage = new Label();

    public UserProfile(User user) {

        TextField oldPassword = new TextField();
        Label oldPasswordLabel = new Label("Old Password:");
        Label newPasswordLabel = new Label("New Password:");
        TextField newPassword = new TextField();
        Label confirmPasswordLabel = new Label("Confirm Password:");
        TextField confirmPassword = new TextField();
        Button submitButton = new Button();
        submitButton.setOnAction((event) -> {
            changePassword(oldPassword.getText(), newPassword.getText(), confirmPassword.getText());
        });
        VBox labelBox = new VBox(oldPasswordLabel, newPasswordLabel, confirmPasswordLabel);
        VBox fieldBox = new VBox(oldPassword, newPassword, confirmPassword);
        HBox temp = new HBox(labelBox, fieldBox);

        this.setCenter(temp);
    }

    private void changePassword(String oldPassword, String newPassword, String confirmPassword) {
        if (newPassword.equals(confirmPassword)) {
            //Change password
            errorMessage.setText("Password changed!");
        } else {
            errorMessage.setText("Error: Password confirmation does not match your new password.");
        }
    }
}