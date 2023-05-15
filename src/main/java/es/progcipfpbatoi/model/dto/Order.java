package es.progcipfpbatoi.model.dto;

import es.progcipfpbatoi.model.dto.producttypes.Product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Objects;

public class Order {

    private String code;

    private String clientName;

    private String createdOn;

    private boolean served;

    private ArrayList<Product> products;

    public Order(String code, String clientName, String createdOn) {
        this.clientName = clientName;
        this.createdOn = createdOn;
        this.code = code;
        served = false;
        this.products = new ArrayList<>();
    }

    public Order(String code) {
        this.code = code;
        this.clientName = "anonimo";
        DateTimeFormatter formateador = new DateTimeFormatterBuilder().parseCaseInsensitive().append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toFormatter();
        this.createdOn = LocalDateTime.now().format(formateador);
        this.products = new ArrayList<>();
    }
    public boolean empiezaPor(String text) {
        return this.code.startsWith(text);
    }

    public void addNewProduct(Product product) {
        products.add(product);
    }

    @Override
    public String toString() {
        return  " Code : " + code +
                ", Name : " + clientName +
                ", CreatedOn : " + createdOn + "\n" +
                "NºProductos : " + products.size() + " " +
                "Precio Total : " + getOrderPrize();
    }

    public String getClientName() {
        return clientName;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public boolean isServed() {
        return served;
    }

    public String getCode() {
        return code;
    }

    public void setServed() {
         served = true;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public boolean hasProducts() {
        return products.size() > 0;
    }

    public double getOrderPrize() {
        double totalPrize = 0;
        for (Product product : products) {
            totalPrize += product.getPrize();
        }
        return totalPrize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order)) {
            return false;
        }
        Order order = (Order) o;
        return code.equals(order.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

}
