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

/**
 * Manages all the functions for statistics and reads it from Receipts.
 */
public class StatisticsManager {
    private ArrayList<Statistics> stats = new ArrayList<>();
    private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    private String vendor = UserManager.getLoggedInUser().getVendor();

    /**
     * Constructor for statistics. It grabs all the items in the receipts and adds them into the ArrayList, and if
     * the item already exist, it will instead just update the item with the information needed (the quantity purchased.)
     * @throws IOException if the file could not be found.
     */
    public StatisticsManager() throws IOException {
        String vendor = UserManager.getLoggedInUser().getVendor();
        List<File> fileList = Files.walk(Paths.get("data/Receipts"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());

        for (File receipt: fileList) {
            ArrayList<Item> receiptItems = gson.fromJson(new FileReader(receipt), new TypeToken<ArrayList<Item>>() {}.getType());
            for (Item receiptItem : receiptItems) {
                if (receiptItem.getVendorName().equals(vendor)) {
                    boolean itemExists = false;
                    for (Statistics itemStat : stats) {
                        if (itemStat.getItem().getID() == receiptItem.getID()) {
                            itemStat.add(receiptItem.getQuantity());
                            itemExists = true;
                            break;
                        }
                    }
                    if (!itemExists) {
                        stats.add(new Statistics(receiptItem));
                    }
                }
            }
        }
    }

    /**
     * Checks to see if any items of the vendor has less than 10 quantity.
     * @return the list of items with low quantity.
     * @throws IOException if StoreManager could not read the files.
     */
    public ArrayList<Item> checkLowQuantity() throws IOException {
        ArrayList<Item> lowQuantity = new ArrayList<>();
        for (Item item: new StoreManager().getItems()){
            if(item.getVendorName().equals(vendor)){
                if (item.getAvailableQuantity() < 10 && item.getAvailableQuantity() != 0)
                    lowQuantity.add(item);
            }
        }
        return lowQuantity;
    }
    /**
     * Checks to see if any items of the vendor has no quantity.
     * @return the list of items with no quantity.
     * @throws IOException if StoreManager could not read the files.
     */
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

    /**
     * Gets the item that has been purchased the most.
     * @return the top selling item.
     */
    public Item getTopSelling() {
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
        if (topSelling == null) {
            return null;
        }
        return topSelling.getItem();
    }
    /**
     * Gets the item that has been purchased the least.
     * @return the least selling item.
     */
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
        if (topSelling == null) {
            return null;
        }
        return topSelling.getItem();
    }
    /**
     * Gets the item that is the most profitable.
     * @return the most profitable item.
     */
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
        if (mostProfitable == null) {
            return null;
        }
        return mostProfitable.getItem();
    }
    /**
     * Gets the item that is the least profitable.
     * @return the least profitable item.
     */
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
        if (leastProfitable == null) {
            return null;
        }
        return leastProfitable.getItem();
    }

    /**
     * calculates the total profit of all of the items.
     * @return the total profit made.
     */
    public double profit(){
        double totalProfit = 0;
        for(Statistics stat: stats){
            totalProfit += stat.getProfit();
        }
        return totalProfit;
    }
}
