package cop4331.gui;

import cop4331.client.ItemManager;
import cop4331.client.UserManager;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class CreateItem extends BorderPane {

    private TextField name = new TextField();
    private TextField description = new TextField();
    private TextField price = new TextField();
    private TextField quantity = new TextField();
    private TextField vendorName = new TextField();
    private FileChooser photo = new FileChooser();
    private Button photoButton = new Button("Choose Photo");
    private Label error = new Label("");
    private String photoPath;
    public CreateItem(String vendorName) {
        this.vendorName.setText(vendorName);
        this.vendorName.setEditable(false);
        this.vendorName.setDisable(true);

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Name: "),0, 0);
        gridPane.add(name,1, 0);
        gridPane.add(new Label("Description: "),0, 1);
        gridPane.add(description,1, 1);
        gridPane.add(new Label("Price: "),0, 2);
        gridPane.add(price,1, 2);
        gridPane.add(new Label("Quantity"),0, 3);
        gridPane.add(quantity,1, 3);
        gridPane.add(new Label("Vendor"),0, 4);
        gridPane.add(this.vendorName,1, 4);
        gridPane.add(new Label("Photo"),0, 5);
        gridPane.add(photoButton,1, 5);
        gridPane.add(error,0, 6);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(5);

        photoButton.setOnAction((event) -> {
            File photoFile =  this.photo.showOpenDialog(PageManager.getInstance().getStage());
            if (photoFile != null){
                String extension = photoFile.getName().substring(1+photoFile.getName().lastIndexOf(".")).toLowerCase();
                try {
                    if(!new File("data/Photos/"+photoFile.getName()).exists()) {
                        ImageIO.write(ImageIO.read(photoFile), extension, new File("data/Photos/" + photoFile.getName()));
                        photoPath = "data/Photos/"+photoFile.getName();
                    }
                    else error.setText("Error! File already exists.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button submit = new Button("Submit");
        gridPane.add(submit,0,8);
        submit.setOnAction((event) -> {
            try {
                double pDouble = new Double(price.getText());
                int qInt = new Integer(quantity.getText());
                ItemManager.addItem(UserManager.getLoggedInUser().getVendor(), name.getText(),description.getText(),pDouble,qInt,qInt,photoPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                PageManager.getInstance().setPage(new StorePage());
            } catch (IOException | CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });

        this.setCenter(gridPane);
        setAlignment(submit, Pos.CENTER);
    }

}
