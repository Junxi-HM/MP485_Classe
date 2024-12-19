package main;

import java.util.ArrayList;
import model.Product;
import model.Sale;
import java.util.Scanner;
import model.Amount;

public class Shop {

    private Amount cash = new Amount(100);
    private Product[] inventory;
    private int numberProducts;
    private ArrayList<Sale> sales;

    final static double TAX_RATE = 1.04;

    public Shop() {
        inventory = new Product[10];
        sales = new ArrayList<>();
    }

    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.loadInventory();

        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        boolean exit = false;

        do {
            System.out.println("\n");
            System.out.println("===========================");
            System.out.println("Menu principal miTienda.com");
            System.out.println("===========================");
            System.out.println("1) Contar caja");
            System.out.println("2) Anadir producto");
            System.out.println("3) Anadir stock");
            System.out.println("4) Marcar producto proxima caducidad");
            System.out.println("5) Ver inventario");
            System.out.println("6) Venta");
            System.out.println("7) Ver ventas");
            System.out.println("8) Ver cantidad total");
            System.out.println("10) Salir programa");
            System.out.print("Seleccione una opcion: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    shop.showCash();
                    break;

                case 2:
                    shop.addProduct();
                    break;

                case 3:
                    shop.addStock();
                    break;

                case 4:
                    shop.setExpired();
                    break;

                case 5:
                    shop.showInventory();
                    break;

                case 6:
                    shop.sale();
                    break;

                case 7:
                    shop.showSales();
                    break;
                case 8:
                    shop.showAllSale();
                    break;
                case 10:
                    exit = true;
                    break;
            }
        } while (exit != true);
    }

    /**
     * load initial inventory to shop
     */
    public void loadInventory() {
        addProduct(new Product("Manzana", 10.00, true, 10));
        addProduct(new Product("Pera", 20.00, true, 20));
        addProduct(new Product("Hamburguesa", 30.00, true, 30));
        addProduct(new Product("Fresa", 5.00, true, 20));
    }

    /**
     * show current total cash
     */
    private void showCash() {
        System.out.println("Dinero actual: " + cash);
    }

    /**
     * add a new product to inventory getting data from console
     */
    public void addProduct() {
        boolean existe = false;
        if (isInventoryFull()) {
            System.out.println("No se pueden anadir mas productos");
            return;
        } else {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nombre: ");
            String name = scanner.nextLine();

            for (Product product : inventory) {
                if (product != null) {
                    if (product.getName().equalsIgnoreCase(name)) {
                        existe = true;
                    }
                }
            }
            if (existe == true) {
                System.out.println("Producto ya existe!!");
                return;
            } else {
                System.out.print("Precio mayorista: ");
                double wholesalerPrice = scanner.nextDouble();
                System.out.print("Stock: ");
                int stock = scanner.nextInt();

                addProduct(new Product(name, wholesalerPrice, true, stock));

            }
        }
    }

    /**
     * add stock for a specific product
     */
    public void addStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.next();
        Product product = findProduct(name);

        if (product != null) {
            // ask for stock
            System.out.print("Seleccione la cantidad a anadir: ");
            int stock = scanner.nextInt();
            // update stock product
            product.setStock(product.getStock() + stock);
            System.out.println("El stock del producto " + name + " ha sido actualizado a " + product.getStock());

        } else {
            System.out.println("No se ha encontrado el producto con nombre " + name);
        }
    }

    /**
     * set a product as expired
     */
    private void setExpired() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.next();

        Product product = findProduct(name);

        if (product != null) {
            product.setPublicPrice(new Amount(product.getPublicPrice().getValue() * 0.6));
            System.out.println("El stock del producto " + name + " ha sido actualizado a " + product.getPublicPrice());
        }
    }

    /**
     * show all inventory
     */
    public void showInventory() {
        System.out.println("Contenido actual de la tienda:");
        for (Product product : inventory) {
            if (product != null) {
                System.out.println(product.getName());
            }
        }
    }

    /**
     * make a sale of products to a client
     */
    public void sale() {
        // ask for client name
        ArrayList<Product> productSale = new ArrayList<Product>();
        Product[] products = new Product[10];
        Scanner sc = new Scanner(System.in);
        System.out.println("Realizar venta, escribir nombre cliente");
        String client = sc.nextLine();
        // sale product until input name is not 0
        Amount totalAmount = new Amount(0.0);
        String name = "";
        while (!name.equals("0")) {
            System.out.println("Introduce el nombre del producto, escribir 0 para terminar:");
            name = sc.nextLine();

            if (name.equals("0")) {
                break;
            }
            Product product = findProduct(name);
            boolean productAvailable = false;

            if (product != null && product.isAvailable()) {
                productAvailable = true;
                totalAmount = new Amount(totalAmount.getValue() + product.getPublicPrice().getValue());
                product.setStock(product.getStock() - 1);
                // if no more stock, set as not available to sale
                if (product.getStock() == 0) {
                    product.setAvailable(false);
                }
                System.out.println("Producto anadido con exito");
                productSale.add(product);
            }

            if (!productAvailable) {
                System.out.println("Producto no encontrado o sin stock");
            }
        }

        // show cost total
        totalAmount = new Amount(totalAmount.getValue() * TAX_RATE);
        cash = new Amount(cash.getValue() + totalAmount.getValue());
        System.out.println("Venta realizada con exito, total: " + totalAmount);
        Product[] proSale = new Product[productSale.size()];
        for (int i = 0; i < productSale.size(); i++) {
            proSale[i] = productSale.get(i);
        }
        Sale sale = new Sale(client, proSale, totalAmount);
        sales.add(sale);
    }

    /**
     * show all sales
     */
    private void showSales() {
        System.out.println("Lista de ventas:");

        for (Sale sale : sales) {
            if (sale != null) {
                System.out.println(sale);
            }
        }
    }

    /**
     * add a product to inventory
     *
     * @param product
     */
    
    public void showAllSale(){
        Amount totalAmount = new Amount(0.0);
    for (Sale sale : sales) {
            if (sale != null) {
                totalAmount = new Amount(totalAmount.getValue() + sale.getAmount().getValue());
            }
        }
        System.out.println(totalAmount);
    }
    
    
    public void addProduct(Product product) {
        if (isInventoryFull()) {
            System.out.println("No se pueden anadir mas productos, se ha alcanzado el maximo de " + inventory.length);
            return;
        }
        inventory[numberProducts] = product;
        numberProducts++;
    }

    /**
     * check if inventory is full or not
     *
     * @return true if inventory is full
     */
    public boolean isInventoryFull() {
        if (numberProducts == 10) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * find product by name
     *
     * @param name
     * @return product found by name
     */
    public Product findProduct(String name) {
        for (int i = 0; i < inventory.length; i++) {
            if (inventory[i] != null && inventory[i].getName().equals(name)) {
                return inventory[i];
            }
        }
        return null;
    }

}
