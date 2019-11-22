package shoppingcart.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import shoppingcart.User;

import java.io.FileNotFoundException;

public class UserPage extends BorderPane {
    GridPane grid = new GridPane();
    User userManager = User.getInstance();
    public UserPage(){

        Text title = new Text("Shopping Cart");
        Label username = new Label("Username:");
        Label password = new Label("Password:");
        Label error = new Label();
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button logIn = new Button("Log In");
        Button makeAcc = new Button("New Account");

        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        grid.add(title,0,0);
        grid.add(username,0,1);
        grid.add(usernameField,1,1);
        grid.add(password,0,2);
        grid.add(passwordField,1,2);
        grid.add(logIn,0,3);
        grid.add(makeAcc,1,3);
        grid.add(error,0,4,2,1);
        this.setCenter(grid);

        logIn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (usernameField.getText().length() != 0){
                    if(passwordField.getText().length() != 0){
                        try {
                            if(userManager.checkUser(usernameField.getText(),passwordField.getText())){
                                PageManager.getInstance().setPage(new StorePage());
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    else error.setText("Error! Please enter your password.");
                }
                else error.setText("Error! Please enter your username.");
            }
        });
    }
}
