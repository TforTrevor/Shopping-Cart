package cop4331.gui;

import cop4331.client.User;
import cop4331.client.UserManager;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.io.IOException;


public class UserProfile extends BorderPane {

    private TextField confirmPassword = new TextField();
    private TextField newPassword = new TextField();
    private TextField oldPassword = new TextField();
    private Label errorMessage = new Label();

    public UserProfile(User user) {

        Text title = new Text("Your Profile");
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

        this.setTop(content);

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
            try {
                changePassword();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button signOutButton = new Button("Sign Out");
        signOutButton.setOnAction((event) -> {
            signOut();
        });

        VBox temp = new VBox(gridPane, submitButton, errorMessage, signOutButton);
        temp.setAlignment(Pos.CENTER);
        temp.setFillWidth(false);
        temp.setSpacing(5);

        this.setCenter(temp);
    }

    private void changePassword() throws IOException {
        if (newPassword.getText().equals(confirmPassword.getText())) {
            UserManager.changePassword(newPassword.getText());
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