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

public class StoreManager {

    private ArrayList<String> fileNames;
    private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

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

    public ArrayList<Item> getItems() throws FileNotFoundException {
        ArrayList<Item> itemList = new ArrayList<>();
        for (String vendorItems : fileNames) {
            ArrayList<Item> items = gson.fromJson(new FileReader(vendorItems), new TypeToken<ArrayList<Item>>() {
            }.getType());
            for (Item item : items) {
                item.setVendorName(vendorItems);
                item.setAvailableQuantity(item.getAvailableQuantity());
                item.setCartQuantity(item.getCartQuantity());
                itemList.add(item);
            }

        }
        return itemList;
    }
    public void setAvailableQuantities(Item item, int newQuantity) throws IOException { //this is where i need help with json
        //basically, i want it to update the available quantities of items in the json file so that it decreases when they add
        //it to the cart. Right now, whenever i leave and reopen the store page, it resets the quantities to default (since
        //it reads from the file). so i need it to decrease the available quantities whenever something is added to the cart in the
        //json file, so that it stays persistent. (My idea is to read the item from the file, update its quantity with
        //item.setAvailableQuantity(newQuantity) and then save it back to json.)

        for (String vendorItems : fileNames) {
            ArrayList<Item> items = gson.fromJson(new FileReader(vendorItems), new TypeToken<ArrayList<Item>>() {
            }.getType());
            for (Item n : items) {
                if(n.equals(item)){
                    n.setAvailableQuantity(newQuantity);

                    File file = new File(vendorItems);
                    FileWriter writer = new FileWriter(file);

                    gson.toJson(items, writer);

                    writer.flush();
                    writer.close();
                    return;
                }
            }

        }


    }
    public void setCartQuantities(Item item, int newQuantity) throws IOException {

        for (String vendorItems : fileNames) {
            ArrayList<Item> items = gson.fromJson(new FileReader(vendorItems), new TypeToken<ArrayList<Item>>() {
            }.getType());
            for (Item n : items) {
                if (n.equals(item)) {
                    n.setCartQuantity(newQuantity);
                    File file = new File(vendorItems);
                    FileWriter writer = new FileWriter(file);

                    gson.toJson(items, writer);

                    writer.flush();
                    writer.close();
                    return;
                }
            }
        }
    }
    public void removeCartQuantities() throws IOException {

        for (String vendorItems : fileNames) {
            ArrayList<Item> items = gson.fromJson(new FileReader(vendorItems), new TypeToken<ArrayList<Item>>() {
            }.getType());
            for (Item n : items) {
                n.setCartQuantity(0);
                File file = new File(vendorItems);
                FileWriter writer = new FileWriter(file);

                gson.toJson(items, writer);

                writer.flush();
                writer.close();
            }
        }
    }

    public ArrayList<Integer> getCartQuantities() throws IOException {
        ArrayList<Integer> quantities = new ArrayList<>();
        for (String vendorItems : fileNames) {
            ArrayList<Item> items = gson.fromJson(new FileReader(vendorItems), new TypeToken<ArrayList<Item>>() {
            }.getType());
            for (Item n : items) {
                    quantities.add(n.getCartQuantity());
                }
            }
            return quantities;
        }

    }

