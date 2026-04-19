package RestaurentSecondTry;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

class MenuItem
{
  private String name;
  public double cost;

  public MenuItem(String name,double cost)
  {
      this.name=name;
      this.cost=cost;
  }
  public String getName()
  {
      return name;
  }
  public double getCost()
  {
      return cost;
  }
  @Override
  public String toString()
  {
      return name+ " $ - " +String.format("%.2f",cost);
  }
}

class Order{

    private ArrayList<MenuItem> items=new ArrayList<>();
    private double totalCost;
    private String status;

    public Order()
    {
        items=new ArrayList<>();
        totalCost=0.0;
        this.status="preparing";
    }
    public double getTotalCost()
    {
        double totalcost=0;
        for (MenuItem item: items)
        {
            totalcost=totalcost+item.getCost();
        }
        return totalcost;
    }

    public void addItem(MenuItem item)
    {
        items.add(item);
        totalCost=totalCost+ item.cost;
    }
    public void showOrder()
    {
        System.out.println("\nOrder Details : ");

        for(MenuItem item:  items)
        {
            System.out.println(item);
        }
        System.out.println("Total Cost for Order - $"+String.format("%.2f",totalCost));
    }

    public void setStatus(String status)
    {
        this.status=status;
    }
    public String getStatus()
    {
        return status;
    }


}

class Customer
{

    private String name;
    private Order order;
    private boolean hasPaid;
    Scanner scanner=new Scanner(System.in);
    public Customer(String name){

        this.name=name;
        this.hasPaid=false;
        this.order=new Order();
    }

    public void addtoOrder(MenuItem item)
    {
        order.addItem(item);

    }

    public void billgenerate()
    {
        if(hasPaid)
        {
            System.out.println(name+" has already paid the Bill");
        }
        else {
            double total_bill= order.getTotalCost();
            System.out.println("\nTotal Bill for your Order Sir,: $" + String.format("%.2f", total_bill));
            System.out.println("Pay the Bill Here by [ Debit card , UPI , Cash ]");
            double payment_amount=scanner.nextDouble();
            if(payment_amount>=total_bill)
            {
                double balance=payment_amount-total_bill;
                System.out.println("Mr. "+name+" has paid the bill");
                System.out.println("\nHello Sir, Your Balance Amount is "+String.format("%.2f",balance));

                hasPaid=true;
            }
            else {
                double not=total_bill-payment_amount;

                while(not>=0)
                {
                    System.out.println("Sir You Didn't pay the total Bill Kindly You Have to Pay "+String.format("%.2f",not));
                    System.out.println("Pay the Remaining Bill Here by [ Debit card , UPI , Cash ]");
                    double remain=scanner.nextDouble();
                    if(remain>=not)
                    {
                        System.out.println("Mr. "+name+ "Paid the Bill .Your Balance Amount is "+String.format("%.2f",(remain-not)));
                        not=not-remain;
                        hasPaid=true;
                    }
                    else {
                        not=not-remain;
                    }

                }
            }

        }

    }

    public void showOrder()
    {
        System.out.println("\nCustomer Name : " +name);
        order.showOrder();
    }

    public Order getOrder()
    {
        return order;
    }
}


class Table{

    private int table_number;
    private boolean isReserved_table;

    public Table(int table_number)
    {
        this.table_number=table_number;
        this.isReserved_table=false;
    }

    public int getTable_number()
    {
        return table_number;
    }

    public boolean isReserved_table()
    {
        return isReserved_table;
    }

    public void reserve()
    {
        if(isReserved_table)
        {
            System.out.println("Table "+ table_number +" is already Booked");
        }
        else {
            isReserved_table=true;
            System.out.println("Table " +table_number + " is Reserved");
        }
    }

    public void release()
    {
        if(isReserved_table)
        {
            isReserved_table=false;
            System.out.println("Table "+ table_number +" is Now Available for Customer");
        }
        else {

            System.out.println("Table " +table_number + " is not Reserved");
        }
    }




}
public class WorkFlow_of_RestaurentManagement {


    private static ArrayList<Customer> customers=new ArrayList<>();
    private static ArrayList<MenuItem> menus=new ArrayList<>();
    private static ArrayList<Table> tables=new ArrayList<>();

    private static void showMenu()
    {
        System.out.println("\nAvailable Menu Items");

        for(int i=0;i< menus.size();i++)
        {
            System.out.println(i+ "." + menus.get(i));
        }
    }

    public static void showTable()
    {
        System.out.println(" Tables Availability : ");
        for (Table table: tables)
        {
            System.out.println("Table Number :"+table.getTable_number()+" Availability Status :"+(table.isReserved_table() ? " Tables are Reserved " :" Tables are Available"));
        }
    }

    public static void main(String[] args)
    {
        Scanner scanner=new Scanner(System.in);


        menus.add(new MenuItem("pizza",20.50));
        menus.add(new MenuItem("burger",12.40));
        menus.add(new MenuItem("roll",30.24));
        menus.add(new MenuItem("tea",50.36));
        menus.add(new MenuItem("IceCream",36));


        for(int i=0;i<5;i++)
        {

            tables.add(new Table(i));

        }

        while (true)
        {
            System.out.println("\n1.New Customer\n" +
                    "2.Show Menu\n" +
                    "3.Add Order\n" +
                    "4.Show Order\n" +
                    "5..Show Tables\n"+
                    "6.Reserve the Table\n"+
                    "7.Release the Table\n"+
                    "8.Update the Status\n"+
                    "9.Bill for the Order\n"+
                    "10.Exit\n");

        System.out.println("Enter the Option for the further Process :");
        int choose=scanner.nextInt();
            scanner.nextLine();
            switch (choose)
        {
            case 1:
                System.out.println("\nEnter Customer Name :");
                String name=scanner.nextLine();
                customers.add(new Customer(name));
                System.out.println("Customer Added");
                break;

            case 2:
                showMenu();
                break;

            case 3:
                System.out.println("\nEnter Customer Index : ");
                int customer_index= scanner.nextInt();

                if(customer_index<0 || customer_index>=customers.size())
                {
                    System.out.println("Invalid Customer Index :");
                }
                else {
                    System.out.println("\nEnter 5 for Ordering the Meal Items");
                    System.out.println("\nEnter Menu Items Index : ");
                    int menu_index= scanner.nextInt();
                    if(menu_index<0 || menu_index>=menus.size())
                    {
                        System.out.println("Invalid Menu Item Index");
                    }
                    else {
                        customers.get(customer_index).addtoOrder(menus.get(menu_index));
                        System.out.println("Order added to Cart");
                        break;
                    }


                }
                break;

            case 4:
               if(customers.isEmpty())
               {
                   System.out.println("No customer are  found");
               }
               else {
                   for(Customer customer:   customers)
                   {
                       customer.showOrder();
                   }

               }
               break;

            case 5:
                showTable();
                break;

            case 6:
                showTable();
                //reserve table
                System.out.println("Enter the Table Number : ");
                int table_no=scanner.nextInt();
                if(table_no<0 || table_no>=tables.size())
                {
                    System.out.println(" Invalid Table Number ");
                }
                else{
                    tables.get(table_no).reserve();

                }
                break;

            case 7:
                showTable();
                //reserve table
                System.out.println("Enter the Table Number : ");
                int table_num=scanner.nextInt();
                if(table_num<0 || table_num>=tables.size())
                {
                    System.out.println(" Invalid Table Number ");
                }
                else{
                    tables.get(table_num).release();

                }
                break;

            case 8:
                //update order Status
                if(customers.isEmpty())
                {
                    System.out.println("No Customers are Found");
                }
                else{
                    System.out.println("Enter the Individual Customer ID :");
                    int customer_id=scanner.nextInt();
                    if(customer_id < 0 || customer_id>=customers.size())
                    {
                        System.out.println("Invalid Customer ID");
                    }
                    else {
                        System.out.println("Update the Order Status for the Customer : ['preparing','ready','served'] ");
                        String Current_status=scanner.next();
                        customers.get(customer_id).getOrder().setStatus(Current_status);
                        System.out.println("Order has been "+ Current_status);
                    }

                }

            case 9:
                if(customers.isEmpty())
                {
                    System.out.println("No Customer Available");
                }
                else {
                    System.out.println("Enter the customer Index to Pay the Bill");
                    int cus_index=scanner.nextInt();
                    if(cus_index<0 || cus_index>=customers.size())
                    {
                        System.out.println("Invalid Customer ID. Please check the Customer ID");
                    }
                    else {
                        customers.get(cus_index).billgenerate();
                    }
                }
                break;

            case 10:
                System.out.println("Restuarent Closes");
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
