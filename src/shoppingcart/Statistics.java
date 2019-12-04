package shoppingcart;

public class Statistics {
    private Item item;
    private int timesPurchased;
    private int quantityPurchased;
    private double profit;

    public Statistics(Item item){
        this.item = item;
        this.timesPurchased = 1;
        this.quantityPurchased = item.getQuantity();
        this.profit = item.getQuantity()*item.getPrice();
    }

    public void setItem(Item item) {
        this.item = item;
    }
    
    public Item getItem() {
        return item;
    }
    
    public void setTimesPurchased(int timesPurchased) {
        this.timesPurchased = timesPurchased;
    }

    public int getTimesPurchased() {
        return timesPurchased;
    }
    
    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public int getQuantityPurchased() {
        return quantityPurchased;
    }
    
    public void setProfit(int profit) {
        this.profit = profit;
    }

    public double getProfit() {
        return profit;
    }
    public void add(int quantityPurchased){
        this.quantityPurchased += quantityPurchased;
        timesPurchased++;
        profit = quantityPurchased*item.getPrice();
    }
}
