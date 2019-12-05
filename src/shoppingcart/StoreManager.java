package shoppingcart;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Manages the items from the stores, including setting their quantities.
 */
public class StoreManager {

    private static ArrayList<String> fileNames;
    private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    /**
     * Constructor for the StoreManager, which grabs the list of files from the vendor directory.
     * @throws IOException if the directory doesnt exist.
     */
    public StoreManager() throws IOException {
        fileNames = new ArrayList<>();
        List<File> fileList = Files.walk(Paths.get("data/Vendors"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        for (File n : fileList) {
            fileNames.add(String.format("data/Vendors/%s", n.getName()));
        }
    }

    /**
     * Grabs all the items from the vendor files into one list.
     * @return an ArrayList  of all of the items in the vendor files.
     * @throws FileNotFoundException if the file isn't found.
     */
    public ArrayList<Item> getItems() throws FileNotFoundException {
        ArrayList<Item> itemList = new ArrayList<>();
        for (String vendorItems : fileNames) {
            ArrayList<Item> items = gson.fromJson(new FileReader(vendorItems), new TypeToken<ArrayList<Item>>() {
            }.getType());
            for (Item item : items) {
                item.setVendorName(item.getVendorName());
                item.setAvailableQuantity(item.getAvailableQuantity());
                itemList.add(item);
            }

        }
        return itemList;
    }

    /**
     * Updates the vendor file with the new available quantity for the specified item.
     * @param item the item to update.
     * @param newQuantity the new quantity to set the availableQuantity to.
     * @throws IOException if the file could not be written.
     */
    public static void saveAvailableQuantity(Item item, int newQuantity) throws IOException {
        for (String vendorItems : fileNames) {
            ArrayList<Item> items = gson.fromJson(new FileReader(vendorItems), new TypeToken<ArrayList<Item>>() {}.getType());
            for (Item n : items) {
                if(n.equals(item)){
                    n.setAvailableQuantity(newQuantity);

                    File file = new File(vendorItems);
                    FileWriter writer = new FileWriter(file);

                    gson.toJson(items, writer);

                    writer.flush();
                    writer.close();
                    item.setAvailableQuantity(newQuantity);
                    return;
                }
            }

        }
    }

    public static void saveQuantity(Item item, int newQuantity) throws IOException {

        for (String vendorItems : fileNames) {
            ArrayList<Item> items = gson.fromJson(new FileReader(vendorItems), new TypeToken<ArrayList<Item>>() {}.getType());
            for (Item n : items) {
                if(n.equals(item)){
                    n.setQuantity(newQuantity);

                    File file = new File(vendorItems);
                    FileWriter writer = new FileWriter(file);

                    gson.toJson(items, writer);

                    writer.flush();
                    writer.close();
                    item.setQuantity(newQuantity);
                    return;
                }
            }

        }
    }

    public static void createStore(String vendor) throws IOException {
        ArrayList<Item> dummyList = new ArrayList<>();
        String pathName = "data/Vendors/"+vendor+".json";
        File file = new File(pathName);
        file.getParentFile().mkdirs();
        file.createNewFile();
        FileWriter writer = new FileWriter(file);

        gson.toJson(dummyList, writer);

        writer.flush();
        writer.close();

    }

}

