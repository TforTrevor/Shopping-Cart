package shoppingcart.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import shoppingcart.*;

import java.io.IOException;
import java.util.ArrayList;

public class VendorPage extends BorderPane {

    private GridPane grid = new GridPane();

    public VendorPage() throws IOException, CloneNotSupportedException {
        User user = UserManager.getLoggedInUser();
        Text title = new Text(user.getVendor());
        Text subTitle = new Text("Welcome to Your Store!");
        title.setFont(Font.font(30));
        subTitle.setFont(Font.font(16));


        VBox titles = new VBox();
        titles.getChildren().add(title);
        titles.getChildren().add(subTitle);
        titles.setSpacing(0);
        titles.setAlignment(Pos.CENTER);
        BorderPane content = new BorderPane();
        content.setCenter(titles);
        title.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 32));
        subTitle.setFont(Font.font(Font.getDefault().getFamily(), FontWeight.MEDIUM, 20));
        title.setFill(Color.WHITE);
        subTitle.setFill(Color.WHITE);
        content.setPadding(new Insets(10,0,30, 0));
        content.setBackground(new Background(new BackgroundFill(Color.valueOf("333"), CornerRadii.EMPTY, Insets.EMPTY)));

        ArrayList<Item> buffer = ItemManager.getVendorItems(user.getVendor());

        BorderPane mainContent = new BorderPane();
        mainContent.setPadding(new Insets(10,0,0,0));
        ArrayList<Node> nodes = new ArrayList<>();
        if(!(buffer == null)){
            for (Item item : buffer) {

                Button removeItem = new Button("Remove From Store");
                Spinner<Integer> increaseQuantity = new Spinner<>(1, 100, 1);
                Button addQuantity = new Button("Add " + increaseQuantity.getValue() + " To Your Store  ");
                increaseQuantity.getEditor().textProperty().addListener((obs, oldValue, newValue) ->
                        addQuantity.setText("Add " + increaseQuantity.getValue() + " To Your Store  "));
                increaseQuantity.setMaxWidth(15);


                BorderPane buttons = new BorderPane();
                BorderPane quantity = new BorderPane();
                quantity.setLeft(addQuantity);
                quantity.setRight(increaseQuantity);
                buttons.setTop(quantity);
                buttons.setBottom(removeItem);
                removeItem.setAlignment(Pos.CENTER);
                Label quantityLabel = new Label("Quantity: " + item.getQuantity());
                BorderPane fullNode = new BorderPane();
                fullNode.setBottom(buttons);
                fullNode.setCenter(quantityLabel);
                fullNode.setTop(new ItemNode(item));
                nodes.add(fullNode);

                addQuantity.setOnAction(event -> {
                    try {
                        StoreManager.saveQuantity(item, item.getQuantity() + increaseQuantity.getValue());
                        StoreManager.saveAvailableQuantity(item, item.getAvailableQuantity() + increaseQuantity.getValue());
                        quantityLabel.setText("Quantity: " + item.getQuantity());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        PageManager.getInstance().setPage(new VendorPage());
                    } catch (IOException | CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                });

                removeItem.setOnAction(event -> {
                    try {
                        ItemManager.removeItem(item, user.getVendor());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        PageManager.getInstance().setPage(new StorePage());
                    } catch (IOException | CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                });

            }
            VBox itemCarousel = new VBox(content);
            itemCarousel.setSpacing(10);
            itemCarousel.getChildren().add(new Carousel<Node>("Your Items", nodes));

            mainContent.setTop(itemCarousel);
        }

        GridPane actions = new GridPane();
        HBox buttonGroup = new HBox();
        Button newItem = new Button("Create Item");
        newItem.setOnAction((event) -> {
            Modal modal = new Modal("Create Item", new CreateItem(user.getVendor()));
            modal.show(100, 100, 100, 100);
        });
        Button statistics = new Button("View Store statistics");
        statistics.setOnAction(event -> {
            try {
                PageManager.getInstance().setPage(new StatisticsPage());
            } catch (IOException | CloneNotSupportedException e) {
                e.printStackTrace();
            }
        });
        buttonGroup.getChildren().add(newItem);
        buttonGroup.getChildren().add(statistics);
        buttonGroup.setSpacing(5);
        Text actionsTitle = new Text("What would you like to do?");
        actionsTitle.setFont(Font.font(16));
        actions.setAlignment(Pos.TOP_CENTER);
        actions.setPadding(new Insets(40,0, 40, 0));
        actions.setVgap(5);
        actions.add(actionsTitle, 0, 0);
        actions.add(buttonGroup, 0, 1);

        BorderPane test = new BorderPane();
        test.setTop(actions);
        test.setCenter(mainContent);

        this.setCenter(test);

        this.setTop(content);



    }
}
