package cop4331.client;

/**
 * Statistics class to be initialized from the receipts.
 */
public class Statistics {
    private Item item;
    private int timesPurchased;
    private int quantityPurchased;
    private double profit;

    /**
     * Constructor of statistics, which sets the item, quantity purchased, times purchased, and the profit.
     * @param item The item from the receipts to initialize statistics.
     */
    public Statistics(Item item){
        this.item = item;
        this.timesPurchased = 1;
        this.quantityPurchased = item.getQuantity();
        this.profit = item.getQuantity()*item.getPrice();
    }

    /**
     * gets the item.
     * @return the item.
     */
    public Item getItem() {
        return item;
    }

    /**
     * gets the amount of times purchased.
     * @return the amount of times purchased.
     */
    public int getTimesPurchased() {
        return timesPurchased;
    }

    /**
     * gets the amount of quantity purchased.
     * @return the amount of quantity purchased.
     */
    public int getQuantityPurchased() {
        return quantityPurchased;
    }
    /**
     * gets the profit.
     * @return the profit.
     */
    public double getProfit() {
        return profit;
    }

    /**
     * Updates the class when there is more quantity purchased. Used when the item already exists in the ArrayList in
     * StatisticsManager.
     * @param quantityPurchased the updated quantity purchased.
     */
    public void add(int quantityPurchased){
        this.quantityPurchased += quantityPurchased;
        timesPurchased++;
        profit = this.quantityPurchased * item.getPrice();
    }
}
