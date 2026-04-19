package order;

import menu.Menu;
import java.util.ArrayList;

public class Order {
    private ArrayList<Menu> items = new ArrayList<>();
    private double totalCost;
    private String status;

    public Order() {
        items = new ArrayList<>();
        totalCost = 0.0;
        this.status = "preparing";
    }

    public double getTotalCost() {
        double totalcost = 0;
        for (Menu item : items) {
            totalcost = totalcost + item.getCost();
        }
        return totalcost;
    }

    public void addItem(Menu item) {
        items.add(item);
        totalCost = totalCost + item.getCost();
    }

    public void showOrder() {
        System.out.println("\nOrder Details : ");
        for (Menu item : items) {
            System.out.println(item);
        }
        System.out.println("Total Cost for Order - $" + String.format("%.2f", totalCost));
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
