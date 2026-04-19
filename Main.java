import menu.Menu;
import service.OrderService;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static ArrayList<OrderService.Customer> customers = new ArrayList<>();
    private static ArrayList<Menu> menus = new ArrayList<>();
    private static ArrayList<OrderService.Table> tables = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Initializing Menu
        menus.add(new Menu("pizza", 20.50));
        menus.add(new Menu("burger", 12.40));
        menus.add(new Menu("roll", 30.24));
        menus.add(new Menu("tea", 50.36));
        menus.add(new Menu("IceCream", 36));

        // Initializing Tables
        for (int i = 0; i < 5; i++) {
            tables.add(new OrderService.Table(i));
        }

        while (true) {
            System.out.println("\n1.New Customer\n" +
                    "2.Show Menu\n" +
                    "3.Add Order\n" +
                    "4.Show Order\n" +
                    "5.Show Tables\n" +
                    "6.Reserve the Table\n" +
                    "7.Release the Table\n" +
                    "8.Update the Status\n" +
                    "9.Bill for the Order\n" +
                    "10.Exit\n");

            System.out.println("Enter the Option for the further Process :");
            
            if (!scanner.hasNextInt()) {
                scanner.next(); 
                continue;
            }

            int choose = scanner.nextInt();
            scanner.nextLine();

            switch (choose) {
                case 1:
                    System.out.println("\nEnter Customer Name :");
                    String name = scanner.nextLine();
                    customers.add(new OrderService.Customer(name));
                    System.out.println("Customer Added");
                    break;

                case 2:
                    OrderService.showMenu(menus);
                    break;

                case 3:
                    System.out.println("\nEnter Customer Index : ");
                    int customer_index = scanner.nextInt();
                    if (customer_index < 0 || customer_index >= customers.size()) {
                        System.out.println("Invalid Customer Index");
                    } else {
                        System.out.println("\nEnter Menu Items Index : ");
                        int menu_index = scanner.nextInt();
                        if (menu_index < 0 || menu_index >= menus.size()) {
                            System.out.println("Invalid Menu Item Index");
                        } else {
                            customers.get(customer_index).addtoOrder(menus.get(menu_index));
                            System.out.println("Order added to Cart");
                        }
                    }
                    break;

                case 4:
                    if (customers.isEmpty()) {
                        System.out.println("No customers found");
                    } else {
                        for (OrderService.Customer customer : customers) {
                            customer.showOrder();
                        }
                    }
                    break;

                case 5:
                    OrderService.showTable(tables);
                    break;

                case 6:
                    OrderService.showTable(tables);
                    System.out.println("Enter the Table Number : ");
                    int table_no = scanner.nextInt();
                    if (table_no < 0 || table_no >= tables.size()) {
                        System.out.println("Invalid Table Number");
                    } else {
                        tables.get(table_no).reserve();
                    }
                    break;

                case 7:
                    OrderService.showTable(tables);
                    System.out.println("Enter the Table Number : ");
                    int table_num = scanner.nextInt();
                    if (table_num < 0 || table_num >= tables.size()) {
                        System.out.println("Invalid Table Number");
                    } else {
                        tables.get(table_num).release();
                    }
                    break;

                case 8:
                    if (customers.isEmpty()) {
                        System.out.println("No Customers found");
                    } else {
                        System.out.println("Enter the Individual Customer ID :");
                        int customer_id = scanner.nextInt();
                        if (customer_id < 0 || customer_id >= customers.size()) {
                            System.out.println("Invalid Customer ID");
                        } else {
                            System.out.println("Update the Order Status for the Customer : ['preparing','ready','served'] ");
                            String current_status = scanner.next();
                            customers.get(customer_id).getOrder().setStatus(current_status);
                            System.out.println("Order has been " + current_status);
                        }
                    }
                    break;

                case 9:
                    if (customers.isEmpty()) {
                        System.out.println("No Customer Available");
                    } else {
                        System.out.println("Enter the customer Index to Pay the Bill");
                        int cus_index = scanner.nextInt();
                        if (cus_index < 0 || cus_index >= customers.size()) {
                            System.out.println("Invalid Customer ID");
                        } else {
                            customers.get(cus_index).billgenerate();
                        }
                    }
                    break;

                case 10:
                    System.out.println("Restaurant Closes");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid Option. Choose correct Option");
                    break;
            }
        }
    }
}
