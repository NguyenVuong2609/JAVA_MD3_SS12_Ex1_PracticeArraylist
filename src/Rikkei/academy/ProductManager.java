package Rikkei.academy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ProductManager {
    static ArrayList<Product> arr = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("**************************MENU**************************\n" +
                    "1. Add product\n" +
                    "2. Update product by ID\n" +
                    "3. Delete product by ID\n" +
                    "4. Show product list\n" +
                    "5. Search product by name\n" +
                    "6. Sort product by price\n" +
                    "7. Exit");
            System.out.println("Please enter your choice");
            choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    System.out.println("*******Add new product*******");
                    System.out.println("Enter product's name: ");
                    String name = sc.nextLine();
                    System.out.println("Enter product's price: ");
                    int price = Integer.parseInt(sc.nextLine());
                    int productId;
                    if (arr.isEmpty()) {
                        productId = 1;
                    } else {
                        productId = new ProductManager().findMaxId() + 1;
                    }
                    arr.add(new Product(productId, name, price));
                    System.out.println("Add success!");
                    break;
                case 2:
                    System.out.println("Enter an productId to update that product: ");
                    int targetId = Integer.parseInt(sc.nextLine());
                    if (new ProductManager().findProductById(targetId) != null){
                        System.out.println("Enter a new name: ");
                        String newName = sc.nextLine();
                        System.out.println("Enter a new price: ");
                        int newPrice = Integer.parseInt(sc.nextLine());
                        int index = arr.indexOf(new ProductManager().findProductById(targetId));
                        arr.set(index, new Product(targetId,newName,newPrice));
                        System.out.println("Update success");
                    } else {
                        System.out.println("ID does not exist.");
                    }
                    break;
                case 3:
                    System.out.println("Enter an productId to delete that product: ");
                    targetId = Integer.parseInt(sc.nextLine());
                    if (new ProductManager().findProductById(targetId) != null){
                        arr.remove(new ProductManager().findProductById(targetId));
                        System.out.println("Delete success");
                    } else {
                        System.out.println("ID does not exist.");
                    }
                    break;
                case 4:
                    System.out.printf("%s%25s%35s\n", "ProductName", "Price", "ProductID");
                    for (int i = 0; i < arr.size(); i++) {
                        System.out.printf("%5s%30d%30d\n", arr.get(i).getProductName(), arr.get(i).getProductPrice(), arr.get(i).getProductId());
                    }
                    break;
                case 5:
                    System.out.println("Enter a name: ");
                    String targetName = sc.nextLine();
                    for (int i = 0; i < arr.size(); i++) {
                        if (arr.get(i).getProductName().equalsIgnoreCase(targetName)){
                            System.out.println("Your target -->" + arr.get(i));
                        }
                    }
                    break;
                case 6:
                    ArrayList<Product> sortedArr = (ArrayList<Product>) new ProductManager().sortProductByPrice();
                    System.out.println("Sort success");
                    for (int i = 0; i < sortedArr.size(); i++) {
                        System.out.println(sortedArr.get(i).toString());
                    }
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.out.println("No support");
                    break;
            }
        } while (choice != 7);
    }

    public int findMaxId() {
        int max = arr.get(0).getProductId();
        for (int i = 0; i < arr.size(); i++) {
            if (max < arr.get(i).getProductId()) {
                max = arr.get(i).getProductId();
            }
        }
        return max;
    }

    public Product findProductById(int productId){
        for (int i = 0; i < arr.size(); i++) {
            if (productId == arr.get(i).getProductId()){
                return arr.get(i);
            }
        }
        return null;
    }

    public Object sortProductByPrice(){
        ProductComparator productComparator = new ProductComparator();
        Object sortedArr = arr.clone();
        Collections.sort((ArrayList<Product>)sortedArr,productComparator);
        return sortedArr;
    }
}
