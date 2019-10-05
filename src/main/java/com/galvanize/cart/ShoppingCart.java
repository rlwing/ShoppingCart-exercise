package com.galvanize.cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCart {

    private List<LineItem> cart = new ArrayList<>();

//    private LineItem lineItem = null;

    public BigDecimal totalPrice() {
        BigDecimal total = new BigDecimal("0.00");

        for(LineItem item : cart){
            total = total.add(item.price.multiply(BigDecimal.valueOf(item.quantity)));
        }

        return total;
    }

    public boolean isEmpty() {
//        return lineItem == null;
        return cart.isEmpty();
    }

    public void addItem(int quantity, String name, BigDecimal price, boolean onSale) {
        cart.add(new LineItem(quantity, name, price, onSale));
    }

    public void addItem(int quantity, String name, BigDecimal price) {
//        this.lineItem = new LineItem(quantity, name, price);
        cart.add(new LineItem(quantity, name, price));
    }

    public int getQuantity() {
//        return lineItem.quantity;
        int quantity = 0;
        for (LineItem item : cart){
            quantity += item.quantity;
        }
        return quantity;
    }

    public List<LineItem> itemizedList() {
//        return Collections.emptyList();
        return cart;
    }

    public List<LineItem> onSaleItems() {
        return cart.stream().filter(s -> s.onSale).collect(Collectors.toCollection(ArrayList:: new));
    }

    class LineItem {
        int quantity;
        String name;
        BigDecimal price;
        boolean onSale = false;

        private LineItem(int quantity, String name, BigDecimal price) {
            this.quantity = quantity;
            this.name = name;
            this.price = price;
        }

        private LineItem(int quantity, String name, BigDecimal price, boolean onSale) {
            this.quantity = quantity;
            this.name = name;
            this.price = price;
            this.onSale = onSale;
        }
    }

}
