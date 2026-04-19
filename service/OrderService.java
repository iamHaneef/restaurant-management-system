package service;

import menu.Menu;
import order.Order;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderService {

    public static void showMenu(ArrayList<Menu> menus) {
        System.out.println("\nAvailable Menu Items");
        for (int i = 0; i < menus.size(); i++) {
            System.out.println(i + "." + menus.get(i));
        }
    }

    public static void showTable(ArrayList<Table> tables) {
        System.out.println(" Tables Availability : ");
        for (Table table : tables) {
            System.out.println("Table Number :" + table.getTable_number() + " Availability Status :" + (table.isReserved_table() ? " Tables are Reserved " : " Tables are Available"));
        }
    }

    public static class Customer {
        private String name;
        private Order order;
        private boolean hasPaid;
        Scanner scanner = new Scanner(System.in);

        public Customer(String name) {
            this.name = name;
            this.hasPaid = false;
            this.order = new Order();
        }

        public void addtoOrder(Menu item) {
            order.addItem(item);
        }

        public void billgenerate() {
            if (hasPaid) {
                System.out.println(name + " has already paid the Bill");
            } else {
                double total_bill = order.getTotalCost();
                System.out.println("\nTotal Bill for your Order Sir,: $" + String.format("%.2f", total_bill));
                System.out.println("Pay the Bill Here by [ Debit card , UPI , Cash ]");
                double payment_amount = scanner.nextDouble();
                if (payment_amount >= total_bill) {
                    double balance = payment_amount - total_bill;
                    System.out.println("Mr. " + name + " has paid the bill");
                    System.out.println("\nHello Sir, Your Balance Amount is " + String.format("%.2f", balance));
                    hasPaid = true;
                } else {
                    double not = total_bill - payment_amount;
                    while (not > 0) {
                        System.out.println("Sir You Didn't pay the total Bill Kindly You Have to Pay " + String.format("%.2f", not));
                        System.out.println("Pay the Remaining Bill Here by [ Debit card , UPI , Cash ]");
                        double remain = scanner.nextDouble();
                        if (remain >= not) {
                            System.out.println("Mr. " + name + " Paid the Bill .Your Balance Amount is " + String.format("%.2f", (remain - not)));
                            not = not - remain;
                            hasPaid = true;
                        } else {
                            not = not - remain;
                        }
                    }
                }
            }
        }

        public void showOrder() {
            System.out.println("\nCustomer Name : " + name);
            order.showOrder();
        }

        public Order getOrder() {
            return order;
        }
    }

    public static class Table {
        private int table_number;
        private boolean isReserved_table;

        public Table(int table_number) {
            this.table_number = table_number;
            this.isReserved_table = false;
        }

        public int getTable_number() {
            return table_number;
        }

        public boolean isReserved_table() {
            return isReserved_table;
        }

        public void reserve() {
            if (isReserved_table) {
                System.out.println("Table " + table_number + " is already Booked");
            } else {
                isReserved_table = true;
                System.out.println("Table " + table_number + " is Reserved");
            }
        }

        public void release() {
            if (isReserved_table) {
                isReserved_table = false;
                System.out.println("Table " + table_number + " is Now Available for Customer");
            } else {
                System.out.println("Table " + table_number + " is not Reserved");
            }
        }
    }
}
