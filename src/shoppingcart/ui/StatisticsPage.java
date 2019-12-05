package shoppingcart.ui;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import shoppingcart.Item;
import shoppingcart.StatisticsManager;

import java.io.IOException;
import java.util.ArrayList;

public class StatisticsPage extends BorderPane {
    StatisticsManager stats = new StatisticsManager();

    public StatisticsPage() throws IOException, CloneNotSupportedException {
        ArrayList<Item> lowQuantity = stats.checkLowQuantity();
        ArrayList<Item> outOfStock = stats.checkNoQuantity();
        ArrayList<ItemNode> lowQuantityView = new ArrayList<>();
        ArrayList<ItemNode> outOfStockView = new ArrayList<>();
        for(Item item : lowQuantity)
            lowQuantityView.add(new ItemNode(item));
        for(Item item : outOfStock)
            outOfStockView.add(new ItemNode(item));
        VBox itemCarousel = new VBox();
        itemCarousel.setSpacing(10);
        itemCarousel.getChildren().add(new Carousel<>("Low Quantity", lowQuantityView));
        itemCarousel.getChildren().add(new Carousel<>("Out of Stock", outOfStockView));

        ArrayList<Node> mainStats = new ArrayList<Node>();

        if(stats.getTopSelling() != null) {
            Item topSelling = stats.getTopSelling();
            ItemNode topSellingNode = new ItemNode(topSelling);
            Label topS = new Label("Highest Selling Item:");
            BorderPane topSPane = new BorderPane();
            topSPane.setTop(topSellingNode);
            topSPane.setBottom(topS);
            mainStats.add(topSPane);
        }

        if(stats.getLeastSelling() != null) {
            Item leastSelling = stats.getLeastSelling();
            ItemNode leastSellingNode = new ItemNode(leastSelling);
            Label leastS = new Label("Least Selling Item:");
            BorderPane leastSPane = new BorderPane();
            leastSPane.setTop(leastSellingNode);
            leastSPane.setBottom(leastS);
            mainStats.add(leastSPane);
        }

        if(stats.getMostProfitable() != null) {
            Item mostProfitable = stats.getMostProfitable();
            ItemNode mostProfitableNode = new ItemNode(mostProfitable);
            Label mostP = new Label("Most Profitable Item:");
            BorderPane mostPPane = new BorderPane();
            mostPPane.setTop(mostProfitableNode);
            mostPPane.setBottom(mostP);
            mainStats.add(mostPPane);
        }

        if(stats.getLeastProfitable() != null) {
            Item leastProfitable = stats.getLeastProfitable();
            ItemNode leastProfitableNode = new ItemNode(leastProfitable);
            Label leastP = new Label("Least Profitable Item:");
            BorderPane leastPPane = new BorderPane();
            leastPPane.setTop(leastProfitableNode);
            leastPPane.setBottom(leastP);
            mainStats.add(leastPPane);
        }

        itemCarousel.getChildren().add(new Carousel<>("Statistics", mainStats));

        this.setCenter(itemCarousel);

    }
}
