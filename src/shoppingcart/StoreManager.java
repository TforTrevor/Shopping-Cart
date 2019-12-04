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
    private static Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

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
                itemList.add(item);
            }

        }
        return itemList;
    }
    public void saveAvailableQuantity(Item item, int newQuantity) throws IOException {
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
                    return;
                }
            }

        }
    }

    public static void createStore(String vendor) throws IOException {
        ArrayList<Item> dummyList = new ArrayList<>();
        String pathName = "data/Vendors/"+vendor+".json";
        File file = new File(pathName);
        FileWriter writer = new FileWriter(file);

        gson.toJson(dummyList, writer);

        writer.flush();
        writer.close();

    }

}

