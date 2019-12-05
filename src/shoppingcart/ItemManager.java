package shoppingcart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;

/**
 * Manages mostly everything related to items.
 */
public class ItemManager {
    private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    public ItemManager(){

    }

    /**
     * Adds a new item into the store and saves it in the vendor's json.
     * @param vendor vendor name.
     * @param name item name.
     * @param description description of item.
     * @param price price of item.
     * @param quantity quantity of item.
     * @param availableQuantity the available quantity (should be the same as the quantity).
     * @param photo the photo path.
     * @throws IOException If the file is not found or could not write to file.
     */
    public static void addItem(String vendor, String name, String description, double price, int quantity, int availableQuantity, String photo) throws IOException {
        String vendorPath = ("data/Vendors/" + vendor + ".json");
        StoreManager temp = new StoreManager();

        Item newItem = new Item(temp.getItems().size()+1,name, description, price, quantity, availableQuantity, photo);
        ArrayList<Item> previousItems = null;
        ArrayList<Item> buffer;

        if (new File(vendorPath).length() != 0)
            previousItems = gson.fromJson(new FileReader(vendorPath), new TypeToken<ArrayList<Item>>() {
            }.getType());
        if (previousItems != null)
            buffer = new ArrayList<>(previousItems);
        else
            buffer = new ArrayList<>();

        buffer.add(newItem);

        File file = new File(vendorPath);
        FileWriter writer = new FileWriter(file);

        gson.toJson(buffer, writer);

        writer.flush();
        writer.close();
    }

    /**
     * Gets the vendor items from the json.
     * @param vendor the vendor list to get from.
     * @return an arraylist of all of the items.
     * @throws IOException If the file could not be found.
     */
    public static ArrayList<Item> getVendorItems(String vendor) throws IOException {
        String vendorPath = ("data/Vendors/" + vendor + ".json");
        ArrayList<Item> itemList = null;

        itemList = gson.fromJson(new FileReader(vendorPath), new TypeToken<ArrayList<Item>>() {
        }.getType());

        return itemList;
    }

    /**
     * Removes an item from the vendor list.
     * @param item The item to remove.
     * @param vendor the vendor to remove the item from.
     * @throws IOException If the file could not be found.
     */
    public static void removeItem(Item item, String vendor) throws IOException {
        String vendorPath = ("data/Vendors/" + vendor + ".json");
        ArrayList<Item> itemList = null;

        itemList = gson.fromJson(new FileReader(vendorPath), new TypeToken<ArrayList<Item>>() {
        }.getType());

        itemList.remove(item);

        File file = new File(vendorPath);
        FileWriter writer = new FileWriter(file);

        gson.toJson(itemList, writer);

        writer.flush();
        writer.close();

    }

}
