package shoppingcart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsManager {
    private  ArrayList<Statistics> stats = new ArrayList<>();
    private  Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    private String vendor = UserManager.getLoggedInUser().getVendor();

    public StatisticsManager() throws IOException {
        String vendor = UserManager.getLoggedInUser().getVendor();
        List<File> fileList = Files.walk(Paths.get("data/Receipts"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());

        for (File receipt: fileList) {
            ArrayList<Item> receiptItems = gson.fromJson(new FileReader(receipt), new TypeToken<ArrayList<Item>>() {}.getType());
            for(Item receiptItem : receiptItems){
                if(receiptItem.getVendorName().equals(vendor)) {
                    for (Statistics itemStat: stats) {
                        if(itemStat.getItem().getID() == receiptItem.getID()){
                            itemStat.add(receiptItem.getQuantity());
                        }
                        else stats.add(new Statistics(receiptItem));
                    }
                }
            }
        }
    }

    public ArrayList<Item> checkLowQuantity() throws IOException {
        ArrayList<Item> lowQuantity = new ArrayList<>();
        for (Item item: new StoreManager().getItems()){
            if(item.getVendorName().equals(vendor)){
                if(item.getAvailableQuantity() < 10)
                    lowQuantity.add(item);
            }
        }
        return lowQuantity;
    }

    public ArrayList<Item> checkNoQuantity() throws IOException {
        ArrayList<Item> noQuantity = new ArrayList<>();
        for (Item item: new StoreManager().getItems()){
            if(item.getVendorName().equals(vendor)){
                if(item.getAvailableQuantity() == 0)
                    noQuantity.add(item);
            }
        }
        return noQuantity;
    }

    public Item getTopSelling(){
        Statistics topSelling = null;
        for (Statistics itemStat : stats) {
            if (topSelling == null)
                topSelling = itemStat;
            else {
                if(topSelling.getTimesPurchased() < itemStat.getTimesPurchased())
                    topSelling = itemStat;
                else if(topSelling.getTimesPurchased() == itemStat.getTimesPurchased()){
                    if(topSelling.getQuantityPurchased() < itemStat.getQuantityPurchased())
                        topSelling = itemStat;
                }
            }
        }
        return topSelling.getItem();
    }

    public Item getLeastSelling(){
        Statistics topSelling = null;
        for (Statistics itemStat : stats) {
            if (topSelling == null)
                topSelling = itemStat;
            else {
                if(topSelling.getTimesPurchased() > itemStat.getTimesPurchased())
                    topSelling = itemStat;
                else if(topSelling.getTimesPurchased() == itemStat.getTimesPurchased()){
                    if(topSelling.getQuantityPurchased() > itemStat.getQuantityPurchased())
                        topSelling = itemStat;
                }
            }
        }
        return topSelling.getItem();
    }

    public Item getMostProfitable(){
        Statistics mostProfitable = null;
        for (Statistics itemStat : stats) {
            if (mostProfitable == null)
                mostProfitable = itemStat;
            else {
                if(mostProfitable.getProfit() < itemStat.getProfit())
                    mostProfitable = itemStat;
            }
        }
        return mostProfitable.getItem();
    }

    public Item getLeastProfitable(){
        Statistics leastProfitable = null;
        for (Statistics itemStat : stats) {
            if (leastProfitable == null)
                leastProfitable = itemStat;
            else {
                if(leastProfitable.getProfit() < itemStat.getProfit())
                    leastProfitable = itemStat;
            }
        }
        return leastProfitable.getItem();
    }

    public double profit(){
        double totalProfit = 0;
        for(Statistics stat: stats){
            totalProfit += stat.getProfit();
        }
        return totalProfit;
    }
}
