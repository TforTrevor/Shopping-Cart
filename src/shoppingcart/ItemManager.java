package shoppingcart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;

public class ItemManager {
    private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    public ItemManager(){

    }
    public static void addItem(String vendor, String name, String description, double price, int quantity, int availableQuantity, String photo) throws IOException {
        String vendorPath = ("data/Vendors/" + vendor + ".json");
        Item newItem = new Item(name, description, price, quantity, availableQuantity, photo);
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
    public static ArrayList<Item> getVendorItems(String vendor) throws IOException {
        String vendorPath = ("data/Vendors/" + vendor + ".json");
        ArrayList<Item> itemList = null;

        itemList = gson.fromJson(new FileReader(vendorPath), new TypeToken<ArrayList<Item>>() {
        }.getType());

        return itemList;
    }

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
