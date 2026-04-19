package menu;

public class Menu {
    private String name;
    private double cost;

    public Menu(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return name + " $ - " + String.format("%.2f", cost);
    }
}
