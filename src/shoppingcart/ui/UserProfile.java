package shoppingcart.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import shoppingcart.Store;
import shoppingcart.User;
import shoppingcart.UserManager;

import java.util.ArrayList;

public class UserProfile extends BorderPane {

    private TextField confirmPassword = new TextField();
    private TextField newPassword = new TextField();
    private TextField oldPassword = new TextField();
    private Label errorMessage = new Label();

    public UserProfile(User user) {

        Label oldPasswordLabel = new Label("Old Password:");
        Label newPasswordLabel = new Label("New Password:");
        Label confirmPasswordLabel = new Label("Confirm Password:");
        GridPane gridPane = new GridPane();
        gridPane.add(oldPasswordLabel, 0, 0);
        gridPane.add(oldPassword, 1, 0);
        gridPane.add(newPasswordLabel, 0, 1);
        gridPane.add(newPassword, 1, 1);
        gridPane.add(confirmPasswordLabel, 0, 2);
        gridPane.add(confirmPassword, 1, 2);
        gridPane.setHgap(10);
        gridPane.setVgap(5);
        Button submitButton = new Button("Submit");
        submitButton.setOnAction((event) -> {
            changePassword();
        });

        Button signOutButton = new Button("Sign Out");
        signOutButton.setOnAction((event) -> {
            signOut();
        });

        VBox temp = new VBox(gridPane, submitButton, errorMessage, signOutButton);
        temp.setAlignment(Pos.CENTER);
        temp.setFillWidth(false);
        temp.setSpacing(5);

        try {
            ArrayList<ItemNode> itemNodes = new ArrayList<>();
            for (int i = 0; i < 50; i++) {
                itemNodes.add(new ItemNode(new Store().getItems().get(0)));
            }
            temp.getChildren().add(new Carousel<>("Bandages", itemNodes));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Button test = new Button("Create Item");
        test.setOnAction((event) -> {
            Modal modal = new Modal("Create Item", new CreateItem("Test Vendor"));
            modal.show(100, 100, 100, 100);
        });
        temp.getChildren().add(test);

        this.setCenter(temp);
    }

    private void changePassword() {
        if (newPassword.getText().equals(confirmPassword.getText())) {
            //Change password
            oldPassword.setText("");
            newPassword.setText("");
            confirmPassword.setText("");
            errorMessage.setText("Password changed!");
        } else {
            errorMessage.setText("Error: Password confirmation does not match your new password.");
        }
    }

    private void signOut() {
        UserManager.setLoggedInUser(null);
        PageManager.getInstance().setHeader(null);
        PageManager.getInstance().setPage(new Login());
    }
}