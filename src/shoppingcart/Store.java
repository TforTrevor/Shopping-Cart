package shoppingcart;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Store {

    private ArrayList<String> fileNames;
    private Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();

    public Store() throws IOException {
        fileNames = new ArrayList<>();
        List<File> fileList = Files.walk(Paths.get("data/Vendors"))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
        for (File n : fileList) {
            fileNames.add(String.format("data/Vendors/%s",n.getName()));
        }
    }
    public ArrayList<Item> getItems() throws FileNotFoundException {
        ArrayList<Item> itemList = new ArrayList<>();
        for (String vendorItems : fileNames) {
            ArrayList<ItemType> items = gson.fromJson(new FileReader(vendorItems), new TypeToken<ArrayList<ItemType>>() {}.getType());
            for (ItemType item : items) {
                item.setVendorName(vendorItems);
                itemList.add(new Item(item));
            }
        }
        return itemList;
    }
}
