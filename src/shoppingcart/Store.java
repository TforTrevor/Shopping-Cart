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
            //File file = new File(vendorItems);
            //System.out.println(vendorItems);
            //FileWriter writer = new FileWriter(file);

            for(Item i: getItems()){
                if(i.getID() == item.getID()){
                    i.setAvailableQuantity(newQuantity);
                    //System.out.println("Available quantity of: " + i.getName() + " is: " + newQuantity);

                }
            }
           // writer.flush();
            //writer.close();
        }
    }
}
