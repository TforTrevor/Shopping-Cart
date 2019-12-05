package cop4331.gui;

import cop4331.client.Item;
import cop4331.client.StatisticsManager;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
            topSPane.setCenter(topSellingNode);
            topSPane.setTop(topS);
            mainStats.add(topSPane);
        }

        if(stats.getLeastSelling() != null) {
            Item leastSelling = stats.getLeastSelling();
            ItemNode leastSellingNode = new ItemNode(leastSelling);
            Label leastS = new Label("Least Selling Item:");
            BorderPane leastSPane = new BorderPane();
            leastSPane.setCenter(leastSellingNode);
            leastSPane.setTop(leastS);
            mainStats.add(leastSPane);
        }

        if(stats.getMostProfitable() != null) {
            Item mostProfitable = stats.getMostProfitable();
            ItemNode mostProfitableNode = new ItemNode(mostProfitable);
            Label mostP = new Label("Most Profitable Item:");
            BorderPane mostPPane = new BorderPane();
            mostPPane.setCenter(mostProfitableNode);
            mostPPane.setTop(mostP);
            mainStats.add(mostPPane);
        }

        if(stats.getLeastProfitable() != null) {
            Item leastProfitable = stats.getLeastProfitable();
            ItemNode leastProfitableNode = new ItemNode(leastProfitable);
            Label leastP = new Label("Least Profitable Item:");
            BorderPane leastPPane = new BorderPane();
            leastPPane.setCenter(leastProfitableNode);
            leastPPane.setTop(leastP);
            mainStats.add(leastPPane);
        }

        itemCarousel.getChildren().add(new Carousel<>("Statistics", mainStats));

        this.setCenter(itemCarousel);

    }
}
