/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package shopping.system;

/**
 *
 * @author Students
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Adminstrator extends User {
    private String adminName;
    private String email;
    public Adminstrator() {}

    public void getCustomerName(Customer customer){
        customer.viewCustomerName();
    }
    public ShippingCart getCartInfo() {
    	ShippingCart currentCart = new ShippingCart();
        currentCart.viewCartDetails(2);
    	return currentCart;
    }
}

class Category {
    private int categoryId;
    private int departmentId;
    private String categoryName;
    private String description;

    public Category() {
    }
    public void getProductInCategory() {
        System.out.println(this.description);
    }

}


class Customer extends User {
    private String customerName;
    private String address;
    private String email;
    private int phoneNo;
    private String creditCardInfo;
    private String shippingInfo;
    Orders order = new Orders();
    ShippingCart shippingCart = new ShippingCart();

	public Customer() {
    }

    public void viewCustomerName() {
        System.out.println(this.customerName);
    }

}


class Department {

    private int departmentId;
    private String name;
    private String description;
    
	public Department() {
    }

    public void getCategoryInDepartment() {
        System.out.println(this.description);
    }
}


interface Employee {
	
	void getDepartment();

}


class OrderDetail {
	
    private int orderId;
    private int productId;
    private String productName;
    private int quantity;
    private float unityCost;
    private float subtotal;

    ShippingInfo shippingInfo;

    public OrderDetail() {
    }

    public void viewShippingDate() {
        shippingInfo = new ShippingInfo();
        shippingInfo.viewShippingInfo();
    }

    public void viewProductPrice(Product product){
        product.displayPrice();
    }

}


class Orders {

    private int orderId;
    private String dateCreated;
    private String dateShipped;
    private String customerName;
    private String customerId;
    private String status;
    private String shippingId;
    private Customer newCustomer;
    
	public Orders() {
    }

    public void placeOrder(Customer customer) {
        newCustomer = customer;
    }
    
    public void setDateCreated(String date) {
    	this.dateCreated = date;
    }
    
    public String getDateCreated() {
    	return this.dateCreated;
    }

}



class Product {
    private String name;
    private String description;
    private int price;
	public Product() {
    }


    public void displayPrice() {
        System.out.println(this.price);
    }

    public void getProductDetails() {
        System.out.println(this.name+this.description);
    }
}



class SessionManager implements Employee {

    private String userId;
    public String departmentName;
    Orders order = new Orders();
    
    public SessionManager(String usrId, String depName) {
    	this.userId = usrId;
    	this.departmentName = depName;
    }

    public void getOrderDate() {
      System.out.println(order.getDateCreated());
    }

    public void getDepartment() {
        System.out.println(departmentName);
    }

}



class ShippingCart {
	
    private int cartId;
    private int productId;
    public int quantity;
    private int dateAdded;
    Product p;

    public ShippingCart() {
    }

    public void addCartItem() {
        p.getProductDetails();
    }

    public void viewCartDetails(int item) {
        quantity = item;
    }
}


class ShippingInfo {
	
    private int shippingId;
    private String shippingType;
    private int shippingCost;
    private int shippingRegionId;
    private Orders order;

    public ShippingInfo() {
    }

    public void viewShippingInfo() {
        System.out.println(order.getDateCreated());
    }

    public void viewItemInfo(Product product){
        product.getProductDetails();
    }
}


class User {
	
    private String userId;
    private String password;
    private String loginStatus;

    public User() {
      }

    public boolean verifyLogin(String usrnm, String pwd) {
        boolean status = false;
       try {

            Scanner scanForUserInfo = new Scanner(new File("UserInfo.txt"));
            Scanner scan;
            String userInfo;

            while (scanForUserInfo.hasNextLine()) {
                userInfo = scanForUserInfo.nextLine();
                String[] info = userInfo.split(" ");
                //for(int i=0;i<info.length;i++)
                userId = info[0];
                password = info[1];

                if (userId.compareTo(usrnm) == 0 && password.compareTo(pwd) == 0)
                    status = true;
            }


        } catch(FileNotFoundException e){
            System.out.println("File not found: " + e.getMessage());
        }
        return status;
    }
}

public class ShoppingSystem {

    public static void main(String[] args) {

        Adminstrator adminstrator = new Adminstrator();
        String userName, password;

        Scanner scan = new Scanner(System.in);
        User user = new User();

        System.out.println("Welcome in our online shopping system, please enter your username:");
        userName = scan.nextLine();
        System.out.println("Please enter your password:");
        password = scan.nextLine();

        while (!(user.verifyLogin(userName, password)))
        {
            System.out.println("Please try again:");
            System.out.println("Please enter your username:");
            userName = scan.nextLine();
            System.out.println("Please enter your password:");
            password = scan.nextLine();
        }

        System.out.println("You are login successfully!");
        System.out.println(adminstrator.getCartInfo().quantity);
    }
}
